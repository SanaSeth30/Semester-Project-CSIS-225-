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

    public OneDayForecast(String city) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("One Day Forecast - " + city, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        table.add(makeHeaderRow());

        String coordinates = getCoordinates(city);
        DayData data = new DayData(ZonedDateTime.now(), coordinates);
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
            row.add(precipPanel);

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

    private String getCoordinates(String city) {
        if (city.equals("Albany")) {
            return "42.6526,-73.7562";
        } else if (city.equals("New York City")) {
            return "40.7128,-74.0060";
        } else if (city.equals("Buffalo")) {
            return "42.8864,-78.8784";
        } else if (city.equals("Yonkers")) {
            return "40.9312,-73.8988";
        } else if (city.equals("Rochester")) {
            return "43.1566,-77.6088";
        } else if (city.equals("Syracuse")) {
            return "43.0481,-76.1474";
        } else if (city.equals("New Rochelle")) {
            return "40.9115,-73.7824";
        }

        return "42.6526,-73.7562";
    }

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
}