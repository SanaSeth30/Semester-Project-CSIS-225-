import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This panel displays the five day forecast for a selected city.
 * It shows the day, temperature, weather icon, and wind speed.
 * 
 * @author Sana Seth
 * @version Spring 2026
 */
public class FiveDayForecast extends JPanel {

    /**
     * Default constructor used by the homepage.
     */
    public FiveDayForecast() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 230, 255)); // soft lavender
    }

    /**
     * Updates the five day forecast using the selected city's coordinates.
     * 
     * @param coordinates the latitude and longitude of the city
     * @param city        the selected city name
     */
    public void updateData(String coordinates, String city) {
        removeAll();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Five Day Forecast - " + city, JLabel.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);
        title.setForeground(new Color(80, 0, 120)); // deep purple text
        title.setOpaque(true);
        title.setBackground(new Color(240, 230, 255)); // soft lavender

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(new Color(240, 230, 255));
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        table.add(makeHeaderRow());

        // get all five days of data in one API call
        WeekData week = new WeekData(ZonedDateTime.now(), coordinates);
        week.populateData();

        // create one row for each of the next five days
        for (int i = 0; i < 5; i++) {

            JPanel row = new JPanel(new GridLayout(1, 4, 5, 5));
            row.setBackground(new Color(173, 216, 230));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            row.setPreferredSize(new Dimension(850, 120));

            // get the day name
            ZonedDateTime date = ZonedDateTime.now().plusDays(i);
            String dayName = date.getDayOfWeek().toString().substring(0, 3);

            JLabel dayLabel = new JLabel(dayName, JLabel.CENTER);
            dayLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            dayLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(dayLabel);

            JLabel tempLabel = new JLabel((int) week.getTemperature(i) + "°F", JLabel.CENTER);
            tempLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            tempLabel.setForeground(new Color(200, 0, 50));
            tempLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(tempLabel);

            JPanel precipPanel = new JPanel(new BorderLayout());
            precipPanel.setBackground(Color.WHITE);
            precipPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

            // choose icon based on the weather data for this day
            String condition = getWeatherCondition(week, i);
            SmallWeatherIcon icon = new SmallWeatherIcon(condition);

            precipPanel.add(icon, BorderLayout.CENTER);
            row.add(precipPanel);

            JLabel windLabel = new JLabel((int) week.getWindSpeed(i) + " mph", JLabel.CENTER);
            windLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            windLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(windLabel);

            table.add(row);
            table.add(Box.createVerticalStrut(8));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    /**
     * Creates the header row for the forecast table.
     * 
     * @return the header row panel
     */
    private JPanel makeHeaderRow() {
        JPanel headerRow = new JPanel(new GridLayout(1, 4, 5, 5));
        headerRow.setBackground(new Color(230, 210, 255));
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        headerRow.setPreferredSize(new Dimension(850, 40));

        Font headerFont = new Font("Georgia", Font.BOLD, 30);

        JLabel dayHeader = new JLabel("Day", JLabel.CENTER);
        dayHeader.setFont(headerFont);
        dayHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(dayHeader);

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
     * Chooses the weather icon based on the daily weather data.
     * 
     * @param data the weather data for the week
     * @param day  the index of the day
     * @return the matching weather condition
     */
    private String getWeatherCondition(WeekData data, int day) {
        int precip = data.getPrecipitationProbability(day);
        String type = data.getPrecipitationType(day);
        double cloud = data.getCloudCoverPercentage(day);
        double wind = data.getWindSpeed(day);

        if (precip >= 40) {
            if (type.equals("rain")) {
                return "rainy";
            }
            if (type.equals("snow")) {
                return "cloudy";
            }
            return "rainy";
        }

        if (wind >= 20) {
            return "windy";
        }

        if (cloud >= 75) {
            return "cloudy";
        }

        if (cloud >= 35) {
            return "partly cloudy";
        }

        return "sunny";
    }

    /**
     * Test method to run this panel by itself.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Five Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);

        FiveDayForecast panel = new FiveDayForecast();

        String coordinates = "42.6526,-73.7562";
        panel.updateData(coordinates, "Albany");

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}