import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Small helper class for displaying WeatherIcon in table cells
 * 
 * @author Sana Seth
 * @version Spring 2026
 */
public class SmallWeatherIcon extends JPanel {

    private WeatherIcon iconPanel;

    public SmallWeatherIcon(String condition) {
        iconPanel = new WeatherIcon(condition);
        setPreferredSize(new Dimension(90, 70));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        iconPanel.setSize(300, 300);
        iconPanel.paint(g2);

        g2.dispose();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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