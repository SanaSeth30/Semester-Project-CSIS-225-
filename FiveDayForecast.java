import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Design for seven day forecast
 * @author Sana Seth
 * @version Spring 2026
 */
public class FiveDayForecast extends JPanel {

    public FiveDayForecast() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Five Day Forecast", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(8, 4, 3, 3));
        table.setBackground(Color.WHITE);
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        Font headerFont = new Font("Arial", Font.BOLD, 13);

        JLabel dayHeader = new JLabel("Day", JLabel.CENTER);
        dayHeader.setFont(headerFont);
        dayHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(dayHeader);

        JLabel lowHeader = new JLabel("Low", JLabel.CENTER);
        lowHeader.setFont(headerFont);
        lowHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(lowHeader);

        JLabel highHeader = new JLabel("High", JLabel.CENTER);
        highHeader.setFont(headerFont);
        highHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(highHeader);

        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBorder(new LineBorder(Color.BLACK, 1));
        table.add(precipHeader);

        String[] days = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};

        for (String day : days) {

            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(dayLabel);

            JLabel lowLabel = new JLabel("60°F", JLabel.CENTER);
            lowLabel.setForeground(new Color(0, 100, 180)); // cool blue
            lowLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(lowLabel);

            JLabel highLabel = new JLabel("75°F", JLabel.CENTER);
            highLabel.setForeground(new Color(200, 80, 0)); // subtle orange
            highLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(highLabel);

            JLabel precipLabel = new JLabel("20%", JLabel.CENTER);
            precipLabel.setForeground(new Color(0, 100, 180)); // blue
            precipLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.add(precipLabel);
        }

        add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Seven Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);
        frame.add(new FiveDayForecast());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
