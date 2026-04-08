
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
                g.drawLine(151, 120, 151, 90);
                g.drawLine(149, 120, 149, 90);
            }
            if (i == 1) {
                g.drawLine(175, 130, 210, 110); //top right
                g.drawLine(176, 130, 211, 110);
                g.drawLine(174, 130, 209, 110);
            }
            if (i == 2) {
                g.drawLine(180, 150, 220, 150); //right
                g.drawLine(180, 151, 220, 151);
                g.drawLine(180, 149, 220, 149);
            }
            if (i == 3) {
                g.drawLine(175, 170, 210, 190); //bottom right
                g.drawLine(176, 170, 211, 190);
                g.drawLine(174, 170, 209, 190);
            }
            if (i == 4) {
                g.drawLine(150, 180, 150, 210); //bottom
                g.drawLine(151, 180, 151, 210);
                g.drawLine(149, 180, 149, 210);
            }
            if (i == 5) {
                g.drawLine(125, 170, 90, 190); //bottom left
                g.drawLine(126, 170, 91, 190);
                g.drawLine(124, 170, 89, 190);
            }
            if (i == 6) {
                g.drawLine(120, 150, 80, 150); //left
                g.drawLine(120, 151, 80, 151);
                g.drawLine(120, 149, 80, 149);

            }
            if (i == 7) {
                g.drawLine(124, 130, 90, 110); //top left
                g.drawLine(125, 130, 91, 110);
                g.drawLine(123, 130, 89, 110);
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
