import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This panel displays the one day (hourly) forecast for a selected city.
 * It builds a scrollable table showing:
 * time, temperature, weather icon, and wind speed.
 * 
 * @author Sana Seth, Lucas Davey (Only implemented UV message & nighttime icon)
 * @version Spring 2026
 */
public class OneDayForecast extends JPanel {

    /**
     * Default constructor (used by homepage before data is loaded)
     */
    public OneDayForecast() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    /**
     * Constructor that immediately loads data for a given city
     */
    public OneDayForecast(String city) {
        updateData(city);
    }

    /**
     * Rebuilds the entire panel based on the selected city.
     * This is called whenever the dropdown changes.
     */
    public void updateData(String city) {

        // clear previous UI
        removeAll();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // title at the top
        JLabel title = new JLabel("One Day Forecast - " + city, JLabel.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // container that will hold all rows vertically
        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        // add column headers
        table.add(makeHeaderRow());

        // get coordinates and fetch weather data
        String coordinates = getCoordinates(city);
        DayData data = new DayData(ZonedDateTime.now(), coordinates);
        data.populateData();

        // loop through all 24 hours
        for (int i = 0; i < 24; i++) {

            // each row = one hour
            JPanel row = new JPanel(new GridLayout(1, 4, 5, 5));
            row.setBackground(new Color(173, 216, 230));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            row.setPreferredSize(new Dimension(850, 120));

            // TIME COLUMN
            JLabel time = new JLabel(String.format("%02d:00", i), JLabel.CENTER);
            time.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(time);

            // TEMPERATURE COLUMN
            JLabel temp = new JLabel((int) data.getTemperature(i) + "°F", JLabel.CENTER);
            temp.setForeground(new Color(200, 80, 0)); // orange for warmth
            temp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(temp);

            // PRECIP COLUMN (ICON ONLY)
            JPanel precipPanel = new JPanel(new BorderLayout());
            precipPanel.setBackground(Color.WHITE);
            precipPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

            // determine weather condition for icon
            String condition = getWeatherCondition(data, i);

            // create and add icon
            SmallWeatherIcon icon = new SmallWeatherIcon(condition);
            precipPanel.add(icon, BorderLayout.CENTER);

            // gets the UV and prints a message below the weather icons
            int uv = data.getUvIndex(i);
            String message = "";
            if(uv == 0){
                message = "UV " + uv + ": Conditions are safe.";
            }
            else if (uv <= 2) {
                message = "UV " + uv + ": Safe sun conditions. Enjoy the outdoors!";
            } else if (uv <= 5) {
                message = "UV " + uv + ": Consider wearing sunscreen.";
            } else if (uv <= 7) {
                message = "UV " + uv + ": Wear sunscreen & limit time spent in sun.";
            } else if (uv <= 10) {
                message = "UV " + uv + ": Stay indoors, avoid sun exposure";
            }

            JLabel uvMessage = new JLabel(message, JLabel.CENTER);
            uvMessage.setFont(new Font("Georgia", Font.PLAIN, 10));
            precipPanel.add(uvMessage, BorderLayout.SOUTH);

            row.add(precipPanel);

            // WIND COLUMN
            JLabel wind = new JLabel((int) data.getWindSpeed(i) + " mph", JLabel.CENTER);
            wind.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(wind);

            // add row + spacing
            table.add(row);
            table.add(Box.createVerticalStrut(8));
        }

        // make table scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // refresh UI
        revalidate();
        repaint();
    }

    /**
     * Creates the header row for the table
     */
    private JPanel makeHeaderRow() {
        JPanel headerRow = new JPanel(new GridLayout(1, 4, 5, 5));
        headerRow.setBackground(Color.pink);
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        headerRow.setPreferredSize(new Dimension(850, 40));

        Font headerFont = new Font("Georgia", Font.BOLD, 30);

        JLabel timeHeader = new JLabel("Time", JLabel.CENTER);
        timeHeader.setFont(headerFont);
        timeHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(timeHeader);

        JLabel tempHeader = new JLabel("Temp", JLabel.CENTER);
        tempHeader.setFont(headerFont);
        tempHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(tempHeader);

        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(precipHeader);

        JLabel windHeader = new JLabel("Wind", JLabel.CENTER);
        windHeader.setFont(headerFont);
        windHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(windHeader);

        return headerRow;
    }

    /**
     * Converts a city name into coordinates for the API
     */
    private String getCoordinates(String city) {
        if (city.equals("Albany"))
            return "42.6526,-73.7562";
        if (city.equals("New York City"))
            return "40.7128,-74.0060";
        if (city.equals("Buffalo"))
            return "42.8864,-78.8784";
        if (city.equals("Yonkers"))
            return "40.9312,-73.8988";
        if (city.equals("Rochester"))
            return "43.1566,-77.6088";
        if (city.equals("Syracuse"))
            return "43.0481,-76.1474";
        if (city.equals("New Rochelle"))
            return "40.9115,-73.7824";

        return "42.6526,-73.7562";
    }

    /**
     * Determines which icon to show based on weather conditions
     */
    private String getWeatherCondition(DayData data, int hour) {
        String precipitationType = data.getPrecipitationType(hour);
        int precipitationProbability = data.getPrecipitationProbability(hour);
        double cloudCover = data.getCloudCoverPercentage(hour);
        double windSpeed = data.getWindSpeed(hour);

        //puts the nightime icon from 9pm at night to 5am the in morning
        if (hour < 6 || hour >= 20) {
            return "night";
        }

        if (precipitationProbability >= 40) {
            if (precipitationType.equals("rain")) {
                return "rainy";
            }
            if (precipitationType.equals("freezing rain") || precipitationType.equals("sleet")) {
                return "rainy";
            }
            if (precipitationType.equals("snow")) {
                return "cloudy";
            }
        }

        if (windSpeed >= 20)
            return "windy";
        if (cloudCover >= 75)
            return "cloudy";
        if (cloudCover >= 35)
            return "partly cloudy";

        return "sunny";

    }

    /**
     * Test method to run this panel by itself
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("One Day Forecast Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        OneDayForecast panel = new OneDayForecast("Albany");
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}