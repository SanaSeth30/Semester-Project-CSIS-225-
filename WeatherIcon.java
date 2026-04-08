
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
    private boolean sunRays = true;

    //cloud colors
    private final Color CLOUD_WHITE = new Color(230, 230, 240);
    private final Color CLOUD_GRAY = new Color(140, 140, 160);
    private final Color CLOUD_DARK = new Color(70, 70, 90);

    //rain and lightning colors
    private final Color RAIN_BLUE = new Color(80, 140, 200);
    private final Color LIGHTNING_BOLT = new Color(255, 240, 60);

    private static final int DELAY_TIME = 300;

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

        Thread animation = new Thread() {
            public void run() {
                while (true) {
                    sunRays = !sunRays;
                    repaint();
                    try {
                        Thread.sleep(DELAY_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animation.start();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        sunnyDay(g);

    }

    private void sunnyDay(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(120, 120, 60, 60);

        //creates different color rays every other
        for (int i = 0; i < 8; i++) {
            Color rayColor;
            if ((i % 2 == 0 && sunRays) || (i % 2 != 0 && !sunRays)) {
                rayColor = Color.YELLOW; //bright yellow
            } else {
                rayColor = new Color(255, 220, 0); //slightly dimmer yellow
            }
            g.setColor(rayColor);

            if (i == 0) {
                g.drawLine(150, 120, 150, 90); //top

            }
            if (i == 1) {
                g.drawLine(172, 130, 210, 110); //top right

            }
            if (i == 2) {
                g.drawLine(180, 150, 220, 150); //right

            }
            if (i == 3) {
                g.drawLine(175, 170, 210, 190); //bottom right

            }
            if (i == 4) {
                g.drawLine(150, 181, 150, 210); //bottom

            }
            if (i == 5) {
                g.drawLine(125, 170, 90, 190); //bottom left

            }
            if (i == 6) {
                g.drawLine(120, 150, 80, 150); //left

            }
            if (i == 7) {
                g.drawLine(125, 130, 90, 110); //top left
            }
        }
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
