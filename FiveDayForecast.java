import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Design for five day forecast
 * @author Sana Seth
 * @version Spring 2026
 */
public class FiveDayForecast extends JPanel {


    public FiveDayForecast() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }


    public void updateData(String coordinates, String city) {


        removeAll();


        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        JLabel title = new JLabel("Five Day Forecast - " + city, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);


        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));


        table.add(makeHeaderRow());


        for (int i = 0; i < 5; i++) {


            ZonedDateTime date = ZonedDateTime.now().plusDays(i);
            DayData day = new DayData(date, coordinates);
            day.populateData();


            JPanel row = new JPanel(new GridLayout(1, 4, 5, 5));
            row.setBackground(Color.WHITE);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            row.setPreferredSize(new Dimension(850, 120));


            String dayName = date.getDayOfWeek().toString().substring(0, 3);


            JLabel dayLabel = new JLabel(dayName, JLabel.CENTER);
            dayLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(dayLabel);


            JLabel lowLabel = new JLabel((int) day.getDailyLowestTemp() + "°F", JLabel.CENTER);
            lowLabel.setForeground(new Color(0, 100, 180));
            lowLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(lowLabel);


            JLabel highLabel = new JLabel((int) day.getDailyHighestTemp() + "°F", JLabel.CENTER);
            highLabel.setForeground(new Color(200, 80, 0));
            highLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            row.add(highLabel);


            JPanel precipPanel = new JPanel(new BorderLayout());
            precipPanel.setBackground(Color.WHITE);
            precipPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));


            String condition = getWeatherCondition(day);


            SmallWeatherIcon icon = new SmallWeatherIcon(condition);


            int precipChance = day.getPrecipitationProbability(12); // midday


            JLabel precip = new JLabel(precipChance + "%", JLabel.CENTER);
            precip.setForeground(new Color(0, 100, 180));


            precipPanel.add(icon, BorderLayout.CENTER);
            precipPanel.add(precip, BorderLayout.SOUTH);
            row.add(precipPanel);


            table.add(row);
            table.add(Box.createVerticalStrut(8));
        }


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);


        add(scrollPane, BorderLayout.CENTER);


        revalidate();
        repaint();
    }


    private JPanel makeHeaderRow() {
        JPanel headerRow = new JPanel(new GridLayout(1, 4, 5, 5));
        headerRow.setBackground(Color.WHITE);
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


        Font headerFont = new Font("Arial", Font.BOLD, 13);


        JLabel dayHeader = new JLabel("Day", JLabel.CENTER);
        dayHeader.setFont(headerFont);
        dayHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(dayHeader);


        JLabel lowHeader = new JLabel("Low", JLabel.CENTER);
        lowHeader.setFont(headerFont);
        lowHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(lowHeader);


        JLabel highHeader = new JLabel("High", JLabel.CENTER);
        highHeader.setFont(headerFont);
        highHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(highHeader);


        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBorder(new LineBorder(Color.BLACK, 1));
        headerRow.add(precipHeader);


        return headerRow;
    }


    private String getWeatherCondition(DayData data) {


        int precip = data.getPrecipitationProbability(12);
        String type = data.getPrecipitationType(12);
        double cloud = data.getCloudCoverPercentage(12);
        double wind = data.getWindSpeed(12);


        if (precip >= 40) {
            if (type.equals("rain")) return "rainy";
            if (type.equals("snow")) return "cloudy";
            return "rainy";
        }


        if (wind >= 20) return "windy";
        if (cloud >= 75) return "cloudy";
        if (cloud >= 35) return "partly cloudy";


        return "sunny";
    }
    public static void main(String[] args) {
    JFrame frame = new JFrame("Test Five Day Forecast");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 800);


    FiveDayForecast panel = new FiveDayForecast();


    // test with Albany coords
    String coordinates = "42.6526,-73.7562";
    panel.updateData(coordinates, "Albany");


    frame.add(panel);


    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
}

