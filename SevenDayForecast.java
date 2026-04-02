import java.awt.*;
import javax.swing.*;

/**
 * Design for seven day forecast
 * @author Sana Seth
 * @version Spring 2026
 */


public class SevenDayForecast extends JPanel {

    public SevenDayForecast() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("7 Day Forecast", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(8, 4));

        // headers
        table.add(new JLabel("Day"));
        table.add(new JLabel("Low"));
        table.add(new JLabel("High"));
        table.add(new JLabel("Precip"));

        String[] days = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};

        for (String day : days) {
            table.add(new JLabel(day));
            table.add(new JLabel("60°F"));
            table.add(new JLabel("75°F"));
            table.add(new JLabel("20%"));
        }

        add(table, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("7 Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.add(new SevenDayForecast());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}