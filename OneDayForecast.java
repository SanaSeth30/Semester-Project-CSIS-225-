import java.awt.*;
import javax.swing.*;

/**
 * Design for one day forecast
 * @author Sana Seth
 * @version Spring 2026
 */
public class OneDayForecast extends JPanel {

    public OneDayForecast() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("One Day Forecast", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(14, 4));

        Font headerFont = new Font("Arial", Font.BOLD, 12);

        JLabel timeHeader = new JLabel("Time");
        table.add(timeHeader);

        JLabel tempHeader = new JLabel("Temp");
        table.add(tempHeader);

        JLabel precipHeader = new JLabel("Precip");
        table.add(precipHeader);

        JLabel windHeader = new JLabel("Wind");
        table.add(windHeader);

        for (int i = 0; i < 13; i++) {
            table.add(new JLabel(String.format("%02d:00", i)));
            table.add(new JLabel("70°F"));
            table.add(new JLabel("10%"));
            table.add(new JLabel("5 mph"));
        }

        add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("One Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new OneDayForecast());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}