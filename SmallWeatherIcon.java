import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Small helper class for displaying WeatherIcon in table cells
 * 
 * @author Sana Seth & Lucas Davey
 * @version Spring 2026
 */
public class SmallWeatherIcon extends JPanel {
    public SmallWeatherIcon(String condition) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        WeatherIcon icon = new WeatherIcon(condition);
        icon.setPreferredSize(new Dimension(60,60));
        icon.startAnimation();
        add(icon);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.add(new SmallWeatherIcon("rainy"));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}