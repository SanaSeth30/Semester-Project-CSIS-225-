
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

    // cloud colors
    private final Color CLOUD_WHITE = new Color(230, 230, 240);
    private final Color CLOUD_GRAY = new Color(140, 140, 160);
    private final Color CLOUD_DARK = new Color(70, 70, 90);

    // rain and lightning colors
    private final Color RAIN_BLUE = new Color(80, 140, 200);
    private final Color LIGHTNING_BOLT = new Color(255, 240, 60);

    private static final int DELAY_TIME = 350;

    // cloud instance variables
    private int moveCloud = 0;
    private int cloudDirection = 1;

    // instance variable for rain so they all drop at different times
    private int rain1 = 0;
    private int rain2 = 20;
    private int rain3 = 40;
    private int rain4 = 60;

    // instance variable for thunderstorm method
    private boolean lightningFlash = false;

    // instance variables for windyDay() method
    private int windMove = 0;
    private int windDirection = 1;

    /**
     * creates the weather icon condition
     * 
     * @param condition the type of weather condition
     */
    public WeatherIcon(String condition) {
        this.weatherCondition = condition;
        setPreferredSize(new Dimension(60, 60));
    }

    /**
     * starts the animation for the weather condition
     */
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
                    lightningFlash = !lightningFlash;

                    moveCloud += cloudDirection * 2; // moves cloud right
                    if (moveCloud > 15 || moveCloud < -40) {
                        cloudDirection *= -1; // moves cloud back left
                    }

                    windMove += windDirection * 3;
                    if (windMove > 20 || windMove < -20) {
                        windDirection *= -1; // reverse direction
                    }

                    rain1 += 6;
                    rain2 += 6;
                    rain3 += 6;
                    rain4 += 6;
                    if (rain1 > 80) {
                        rain1 = 0;
                    }
                    if (rain2 > 80) {
                        rain2 = 0;
                    }
                    if (rain3 > 80) {
                        rain3 = 0;
                    }
                    if (rain4 > 80) {
                        rain4 = 0;
                    }

                    repaint();
                    try {
                        Thread.sleep(DELAY_TIME);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        animation.start();

    }

    /**
     * draws the appropriate weather icon based on condition
     * 
     * @param g the graphic used for drawing
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        String condition = weatherCondition.toLowerCase();

        if (condition.equals("sunny")) {
            sunnyDay(g, w, h);
        } else if (condition.equals("partly cloudy")) {
            partlyCloudy(g, w, h);
        } else if (condition.equals("cloudy")) {
            cloudyDay(g, w, h);
        } else if (condition.equals("rainy")) {
            rainyDay(g,w,h);
        } else if (condition.equals("thunderstorm")) {
            thunderstorm(g,w,h);
        } else if (condition.equals("windy")) {
            windyDay(g,w,h);
        }

    }

    /**
     * draws sunny icon
     * 
     * @param g the graphic used for drawing
     */
    private void sunnyDay(Graphics g, int w, int h) {
        g.setColor(Color.YELLOW);
        g.fillOval(120 * w / 300, 120 * h / 300, 60 * w / 300, 60 * h / 300); 

        // creates different color rays every other
        for (int i = 0; i < 8; i++) {
            Color rayColor;
            if ((i % 2 == 0 && sunRays) || (i % 2 != 0 && !sunRays)) {
                rayColor = Color.YELLOW; // bright yellow
            } else {
                rayColor = new Color(255, 220, 0); // slightly dimmer yellow
            }
            g.setColor(rayColor);

            if (i == 0) {
                g.drawLine(150 * w / 300, 120 * h / 300, 150 * w / 300, 90 * h / 300); // top
                g.drawLine(151 * w / 300, 120 * h / 300, 151 * w / 300, 90 * h / 300);
                g.drawLine(149 * w / 300, 120 * h / 300, 149 * w / 300, 90 * h / 300);
            }
            if (i == 1) {
                g.drawLine(175 * w / 300, 130 * h / 300, 210 * w / 300, 110 * h / 300); // top right
                g.drawLine(176 * w / 300, 130 * h / 300, 211 * w / 300, 110 * h / 300);
                g.drawLine(174 * w / 300, 130 * h / 300, 209 * w / 300, 110 * h / 300);
            }
            if (i == 2) {
                g.drawLine(180 * w / 300, 150 * h / 300, 220 * w / 300, 150 * h / 300); // right
                g.drawLine(180 * w / 300, 151 * h / 300, 220 * w / 300, 151 * h / 300);
                g.drawLine(180 * w / 300, 149 * h / 300, 220 * w / 300, 149 * h / 300);
            }
            if (i == 3) {
                g.drawLine(175 * w / 300, 170 * h / 300, 210 * w / 300, 190 * h / 300); // bottom right
                g.drawLine(176 * w / 300, 170 * h / 300, 211 * w / 300, 190 * h / 300);
                g.drawLine(174 * w / 300, 170 * h / 300, 209 * w / 300, 190 * h / 300);
            }
            if (i == 4) {
                g.drawLine(150 * w / 300, 180 * h / 300, 150 * w / 300, 210 * h / 300); // bottom
                g.drawLine(151 * w / 300, 180 * h / 300, 151 * w / 300, 210 * h / 300);
                g.drawLine(149 * w / 300, 180 * h / 300, 149 * w / 300, 210 * h / 300);
            }
            if (i == 5) {
                g.drawLine(125 * w / 300, 170 * h / 300, 90 * w / 300, 190 * h / 300); // bottom left
                g.drawLine(126 * w / 300, 170 * h / 300, 91 * w / 300, 190 * h / 300);
                g.drawLine(124 * w / 300, 170 * h / 300, 89 * w / 300, 190 * h / 300);
            }
            if (i == 6) {
                g.drawLine(120 * w / 300, 150 * h / 300, 80 * w / 300, 150 * h / 300); // left
                g.drawLine(120 * w / 300, 151 * h / 300, 80 * w / 300, 151 * h / 300);
                g.drawLine(120 * w / 300, 149 * h / 300, 80 * w / 300, 149 * h / 300);

            }
            if (i == 7) {
                g.drawLine(124 * w / 300, 130 * h / 300, 90 * w / 300, 110 * h / 300); // top left
                g.drawLine(125 * w / 300, 130 * h / 300, 91 * w / 300, 110 * h / 300);
                g.drawLine(123 * w / 300, 130 * h / 300, 89 * w / 300, 110 * h / 300);
            }
        }
    }

    /**
     * draws partly cloudy icon with moving clouds
     * 
     * @param g the graphic used for drawing
     */
    private void partlyCloudy(Graphics g, int w, int h) {
        g.setColor(Color.YELLOW);
        g.fillOval(90 * w / 300, 110 * h / 300, 60 * w / 300, 60 * h / 300);

        // sun rays
        g.drawLine(120 * w / 300, 110 * h / 300, 120 * w / 300, 80 * h / 300); // top
        g.drawLine(119 * w / 300, 110 * h / 300, 119 * w / 300, 80 * h / 300);
        g.drawLine(121 * w / 300, 110 * h / 300, 121 * w / 300, 80 * h / 300);

        g.drawLine(120 * w / 300, 170 * h / 300, 120 * w / 300, 200 * h / 300);
        g.drawLine(119 * w / 300, 170 * h / 300, 119 * w / 300, 200 * h / 300);// bottom
        g.drawLine(121 * w / 300, 170 * h / 300, 121 * w / 300, 200 * h / 300);

        g.drawLine(90 * w / 300, 140 * h / 300, 60 * w / 300, 140 * h / 300);
        g.drawLine(90 * w / 300, 139 * h / 300, 60 * w / 300, 139 * h / 300);// left
        g.drawLine(90 * w / 300, 141 * h / 300, 60 * w / 300, 141 * h / 300);

        g.drawLine(150 * w / 300, 140 * h / 300, 180 * w / 300, 140 * h / 300);
        g.drawLine(150 * w / 300, 139 * h / 300, 180 * w / 300, 139 * h / 300);// right
        g.drawLine(150 * w / 300, 141 * h / 300, 180 * w / 300, 141 * h / 300);

        g.drawLine(105 * w / 300, 118 * h / 300, 85 * w / 300, 100 * h / 300); // top left
        g.drawLine(106 * w / 300, 118 * h / 300, 86 * w / 300, 100 * h / 300);
        g.drawLine(104 * w / 300, 118 * h / 300, 84 * w / 300, 100 * h / 300);

        g.drawLine(130 * w / 300, 120 * h / 300, 150 * w / 300, 95 * h / 300); // top right
        g.drawLine(131 * w / 300, 120 * h / 300, 151 * w / 300, 95 * h / 300);
        g.drawLine(129 * w / 300, 120 * h / 300, 149 * w / 300, 95 * h / 300);

        g.drawLine(110 * w / 300, 160 * h / 300, 90 * w / 300, 185 * h / 300); // bottom left
        g.drawLine(111 * w / 300, 160 * h / 300, 91 * w / 300, 185 * h / 300);
        g.drawLine(109 * w / 300, 160 * h / 300, 89 * w / 300, 185 * h / 300);

        g.drawLine(130 * w / 300, 160 * h / 300, 150 * w / 300, 185 * h / 300); // bottom right
        g.drawLine(131 * w / 300, 160 * h / 300, 151 * w / 300, 185 * h / 300);
        g.drawLine(129 * w / 300, 160 * h / 300, 149 * w / 300, 185 * h / 300);

        // cloud
        g.setColor(CLOUD_WHITE);
        g.fillOval((130 + moveCloud) * w / 300, 130 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillOval((160 + moveCloud) * w / 300, 110 * h / 300, 60 * w / 300, 50 * h / 300);
        g.fillOval((190 + moveCloud) * w / 300, 130 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillRect((160 + moveCloud) * w / 300, 140 * h / 300, 60 * w / 300, 30 * h / 300);
    }

    /**
     * draws cloudy weather with moving clouds
     * 
     * @param g the graphic used for drawing
     */
    private void cloudyDay(Graphics g, int w, int h) {
        g.setColor(CLOUD_GRAY);
        g.fillOval((80 + moveCloud) * w / 300, 120 * h / 300, 70 * w / 300, 45 * h / 300);
        g.fillOval((120 + moveCloud) * w / 300, 100 * h / 300, 80 * w / 300, 55 * h / 300);
        g.fillOval((170 + moveCloud) * w / 300, 120 * h / 300, 70 * w / 300, 45 * h / 300);
        g.fillRect((120 + moveCloud) * w / 300, 130 * h / 300, 80 * w / 300, 35 * h / 300);

        g.setColor(CLOUD_GRAY);
        g.fillOval((100 - moveCloud) * w / 300, 140 * h / 300, 70 * w / 300, 45 * h / 300);
        g.fillOval((140 - moveCloud) * w / 300, 120 * h / 300, 80 * w / 300, 55 * h / 300);
        g.fillOval((190 - moveCloud) * w / 300, 140 * h / 300, 70 * w / 300, 45 * h / 300);
        g.fillRect((140 - moveCloud) * w / 300, 150 * h / 300, 80 * w / 300, 35 * h / 300);
    }

    /**
     * draws rain condition with cloud
     * 
     * @param g the graphic used for drawing
     */
    private void rainyDay(Graphics g, int w, int h) {
        // cloud
        g.setColor(CLOUD_GRAY);
        g.fillOval(110 * w / 300, 110 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillOval(140 * w / 300, 90 * h / 300, 70 * w / 300, 50 * h / 300);
        g.fillOval(180 * w / 300, 110 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillRect(140 * w / 300, 120 * h / 300, 70 * w / 300, 30 * h / 300);

        // rain
        g.setColor(RAIN_BLUE);

        g.drawLine(140 * w / 300, (150 + rain1) * h / 300, 140 * w / 300, (165 + rain1) * h / 300);
        g.drawLine(160 * w / 300, (150 + rain2) * h / 300, 160 * w / 300, (165 + rain2) * h / 300);
        g.drawLine(180 * w / 300, (150 + rain3) * h / 300, 180 * w / 300, (165 + rain3) * h / 300);
        g.drawLine(200 * w / 300, (150 + rain4) * h / 300, 200 * w / 300, (165 + rain4) * h / 300);
        g.drawLine(220 * w / 300, (150 + rain1) * h / 300, 220 * w / 300, (165 + rain1) * h / 300);

        // puddle
        g.setColor(new Color(100, 160, 220));
        g.fillOval(120 * w / 300, 240 * h / 300, 120 * w / 300, 20 * h / 300);
    }

    /**
     * creates thunderstorm with lightning bolt flashing and cloud
     * 
     * @param g the graphic used for drawing
     */
    private void thunderstorm(Graphics g, int w, int h) {
        // cloud
        g.setColor(CLOUD_DARK);
        g.fillOval(110 * w / 300, 110 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillOval(140 * w / 300, 90 * h / 300, 70 * w / 300, 50 * h / 300);
        g.fillOval(180 * w / 300, 110 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillRect(140 * w / 300, 120 * h / 300, 70 * w / 300, 30 * h / 300);

        // lightning
        if (lightningFlash == false) {
            g.setColor(LIGHTNING_BOLT);

            // slanted rectangle for top part of lightning bolt
            int[] rectX = { 175 * w / 300, 165 * w / 300, 180 * w / 300, 190 * w / 300 };
            int[] rectY = { 130 * h / 300, 165 * h / 300, 175 * h / 300, 140 * h / 300 };

            g.fillPolygon(rectX, rectY, 4);

            // upside down triangle hanging off the right side of rectangle
            int[] triX = { 180 * w / 300, 200 * w / 300, 185 * w / 300 };
            int[] triY = { 160 * h / 300, 170 * h / 300, 220 * h / 300 };

            g.fillPolygon(triX, triY, 3);
        }
    }

    /**
     * draws cloud and has swooshed lines going across screen
     * 
     * @param g the graphic used for drawing
     */
    private void windyDay(Graphics g, int w, int h) {
        // cloud
        g.setColor(CLOUD_GRAY);
        g.fillOval(110 * w / 300, 100 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillOval(140 * w / 300, 80 * h / 300, 70 * w / 300, 50 * h / 300);
        g.fillOval(180 * w / 300, 100 * h / 300, 60 * w / 300, 40 * h / 300);
        g.fillRect(140 * w / 300, 110 * h / 300, 70 * w / 300, 30 * h / 300);

        g.setColor(Color.GRAY);

        // first swoosh upright
        g.drawLine((120 + windMove) * w / 300, 170 * h / 300, (180 + windMove) * w / 300, 170 * h / 300);
        g.drawArc((180 + windMove) * w / 300, 160 * h / 300, 40 * w / 300, 20 * h / 300, 0, 180);

        // second swoosh upside down
        g.drawLine((120 + windMove) * w / 300, 200 * h / 300, (180 + windMove) * w / 300, 200 * h / 300);
        g.drawArc((180 + windMove) * w / 300, 190 * h / 300, 40 * w / 300, 20 * h / 300, 0, -180);

        // third swoosh slightly behind
        g.drawLine((100 + windMove) * w / 300, 185 * h / 300, (160 + windMove) * w / 300, 185 * h / 300);
        g.drawArc((160 + windMove) * w / 300, 175 * h / 300, 40 * w / 300, 20 * h / 300, 0, 180);
    }

    /**
     * main method
     * 
     * @param args
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new WeatherIcon("windy"));

    }

}
