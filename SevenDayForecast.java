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
        setBackground(new Color (175, 240, 255));

        JLabel title = new JLabel("7 Day Forecast", JLabel.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(8, 4, 5, 5));
        table.setBackground(new Color(150, 240, 255));
        table.setBorder(new EmptyBorder(10, 15, 10, 15));

        Font headerFont = new Font ("Georgia", Font.BOLD, 15);
        
        //headers
        JLabel dayHeader = new JLabel("Day", JLabel.CENTER);
        dayHeader.setFont(headerFont);
        dayHeader.setBackground(new Color(180, 210, 255));
        dayHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(dayHeader);

        JLabel lowHeader = new JLabel("Low", JLabel.CENTER);
        lowHeader.setFont(headerFont);

        lowHeader.setBackground(new Color(180, 210, 255));
        lowHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(lowHeader);

        JLabel highHeader = new JLabel("High", JLabel.CENTER);
        highHeader.setFont(headerFont);
        highHeader.setBackground(new Color(180, 210, 255));
        highHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(highHeader);

        JLabel precipHeader = new JLabel("Precip", JLabel.CENTER);
        precipHeader.setFont(headerFont);
        precipHeader.setBackground(new Color(180, 210, 255));
        precipHeader.setBorder(new LineBorder(Color.WHITE, 1));
        table.add(precipHeader);

        String[] days = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setBackground(Color.WHITE);
            dayLabel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(dayLabel);

            JLabel lowLabel = new JLabel("60°F", JLabel.CENTER);
            lowLabel.setForeground(new Color(70, 130, 180)); 
            lowLabel.setBackground(Color.WHITE);
            lowLabel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(lowLabel);

            JLabel highLabel = new JLabel("75°F", JLabel.CENTER);
            highLabel.setForeground(new Color(255, 120, 60));
            highLabel.setBackground(Color.WHITE);
            highLabel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(highLabel);

            JLabel precipLabel = new JLabel("20%", JLabel.CENTER);
            precipLabel.setForeground(new Color(90, 110, 180));
            precipLabel.setBackground(Color.WHITE);
            precipLabel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            table.add(precipLabel);
        }
        add(table, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("7 Day Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        frame.add(new SevenDayForecast());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
