import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Design for one day forecast
 * 
 * @author Sana Seth
 * @version Spring 2026
 */
public class OneDayForecast extends JPanel {

    public OneDayForecast() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("One Day Forecast", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        table.add(makeHeaderRow());

        DayData data = new DayData(ZonedDateTime.now(), "42.6526,-73.7562");
        data.populateData();

        for (int i = 0; i < 24; i++) {
            JPanel row = new JPanel(new GridLayout(1, 4, 5, 5));
            row.setBackground(Color.WHITE);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            row.setPreferredSize(new Dimension(850, 120));

            JLabel time = new JLabel(String.format("%02d:00", i), JLabel.CENTER);
            time.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(time);

            JLabel temp = new JLabel((int) data.getTemperature(i) + "°F", JLabel.CENTER);
            temp.setForeground(new Color(200, 80, 0));
            temp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(temp);

            JPanel precipPanel = new JPanel(new BorderLayout());
            precipPanel.setBackground(Color.WHITE);
            precipPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

            String condition = getWeatherCondition(data, i);

            SmallWeatherIcon icon = new SmallWeatherIcon(condition);

            JLabel precip = new JLabel(data.getPrecipitationProbability(i) + "%", JLabel.CENTER);
            precip.setForeground(new Color(0, 100, 180));

            precipPanel.add(icon, BorderLayout.CENTER);
            precipPanel.add(precip, BorderLayout.SOUTH);
            table.add(precipPanel);

            JLabel wind = new JLabel((int) data.getWindSpeed(i) + " mph", JLabel.CENTER);
            wind.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(wind);

            table.add(row);
            table.add(Box.createVerticalStrut(8));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel makeHeaderRow() {
        JPanel headerRow = new JPanel(new GridLayout(1, 4, 5, 5));
        headerRow.setBackground(Color.WHITE);
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        headerRow.setPreferredSize(new Dimension(850, 40));

        Font headerFont = new Font("Arial", Font.BOLD, 13);

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
     * Matches live weather data to one of the weather icon conditions.
     * 
     * @param data the weather data
     * @param hour the hour of the day
     * @return the matching weather condition string
     */
    private String getWeatherCondition(DayData data, int hour) {
        String precipitationType = data.getPrecipitationType(hour);
        int precipitationProbability = data.getPrecipitationProbability(hour);
        double cloudCover = data.getCloudCoverPercentage(hour);
        double windSpeed = data.getWindSpeed(hour);

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

        if (windSpeed >= 20) {
            return "windy";
        }

        if (cloudCover >= 75) {
            return "cloudy";
        }

        if (cloudCover >= 35) {
            return "partly cloudy";
        }

        return "sunny";
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("One Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);
        frame.add(new OneDayForecast());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}