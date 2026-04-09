import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Design for one day forecast
 * @author Sana Seth
 * @version Spring 2026
 */
public class OneDayForecast extends JPanel {

    public OneDayForecast() {
        setLayout(new BorderLayout());
        setBackground(new Color(150, 240, 255));

        JLabel title = new JLabel("One Day Forecast", JLabel.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(14, 4, 5, 5));
        table.setBackground(new Color(175, 240, 255));
        table.setBorder(new EmptyBorder(10, 15, 10, 15));

        Font headerFont = new Font("Georgia", Font.BOLD, 15);
        JLabel timeHeader = new JLabel("Time", JLabel.CENTER);
        timeHeader.setFont(headerFont);
        timeHeader.setBackground(new Color(180, 210, 255));
        timeHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(timeHeader);

        JLabel tempHeader = new JLabel("Temp", JLabel.CENTER);
        tempHeader.setFont(headerFont);
        tempHeader.setBackground(new Color(180, 210, 255));
        tempHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(tempHeader);

        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBackground(new Color(180, 210, 255));
        precipHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(precipHeader);

        JLabel windHeader = new JLabel("Wind", JLabel.CENTER);
        windHeader.setFont(headerFont);
        windHeader.setBackground(new Color(180, 210, 255));
        windHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(windHeader);

        for (int i = 0; i < 13; i++) {

            JLabel time = new JLabel(String.format("%02d:00", i), JLabel.CENTER);
            time.setBackground(Color.WHITE);
            time.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(time);

            JLabel temp = new JLabel("70°F", JLabel.CENTER);
            temp.setForeground(new Color(255, 120, 60));
            temp.setBackground(Color.WHITE);
            temp.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(temp);

            JLabel precip = new JLabel("10%", JLabel.CENTER);
            precip.setForeground(new Color(70, 130, 180));
            precip.setBackground(Color.WHITE);
            precip.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(precip);

            JLabel wind = new JLabel("5 mph", JLabel.CENTER);
            wind.setForeground(new Color(100, 100, 100));
            wind.setBackground(Color.WHITE);
            wind.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(wind);
        }

        add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("One Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 520);
        frame.add(new OneDayForecast());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
