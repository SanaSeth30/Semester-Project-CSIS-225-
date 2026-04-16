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

        JPanel table = new JPanel(new GridLayout(25, 4, 3, 3));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        Font headerFont = new Font("Arial", Font.BOLD, 13);

        JLabel timeHeader = new JLabel("Time", JLabel.CENTER);
        timeHeader.setFont(headerFont);
        timeHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(timeHeader);

        JLabel tempHeader = new JLabel("Temp", JLabel.CENTER);
        tempHeader.setFont(headerFont);
        tempHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(tempHeader);

        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(precipHeader);

        JLabel windHeader = new JLabel("Wind", JLabel.CENTER);
        windHeader.setFont(headerFont);
        windHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(windHeader);

        DayData data = new DayData(ZonedDateTime.now(), "42.6526,-73.7562"); // Albany coords
        data.populateData();

        for (int i = 0; i < 24; i++) {

            JLabel time = new JLabel(String.format("%02d:00", i), JLabel.CENTER);
            time.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(time);

            JLabel temp = new JLabel((int) data.getTemperature(i) + "°F", JLabel.CENTER);
            temp.setForeground(new Color(200, 80, 0)); // subtle orange
            temp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(temp);

            JLabel precip = new JLabel(data.getPrecipitationProbability(i) + "%", JLabel.CENTER);
            precip.setForeground(new Color(0, 100, 180)); // subtle blue
            precip.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(precip);

            JLabel wind = new JLabel((int) data.getWindSpeed(i) + " mph", JLabel.CENTER);
            wind.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(wind);
        }

        add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("One Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.add(new OneDayForecast());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
