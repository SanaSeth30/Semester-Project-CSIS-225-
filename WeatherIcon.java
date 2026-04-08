
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This program will display the weather icons such as sunny, partly cloudy,
 * cloudy, light rain, moderate/heavy rain, and wind
 *
 *
 * @author Lucas Davey
 * @version Spring 2026
 */
public class WeatherIcon extends JPanel implements Runnable {

    private String weatherCondition;

    //cloud colors
    private final Color CLOUD_WHITE = new Color(230, 230, 240);
    private final Color CLOUD_GRAY = new Color(140, 140, 160);
    private final Color CLOUD_DARK = new Color(70, 70, 90);

    //rain and lightning colors
    private final Color RAIN_BLUE = new Color(80, 140, 200);
    private final Color LIGHTNING_BOLT = new Color(255, 240, 60);

    public WeatherIcon(String condition) {
        this.weatherCondition = condition;
    }

    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Weather: " + weatherCondition);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        sunnyDay(g);

    }

    private void sunnyDay(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(100, 100, 80, 80);

        g.setColor(Color.ORANGE);
        g.drawLine(140, 100, 140, 60); //top
        g.drawLine(180, 140, 220, 140); //right
        g.drawLine(140, 180, 140, 220); //bottom
        g.drawLine(100, 140, 60, 140);  //left

    }

    private void partlyCloudy() {

    }

    private void cloudyDay() {

    }

    private void rainyDay() {

    }

    private void thunderstorm() {

    }

    private void windyDay() {

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new WeatherIcon("sunny"));

    }

}
