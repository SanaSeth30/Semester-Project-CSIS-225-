import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This program will display the weather icons such as
 * sunny, partly cloudy, cloudy, light rain, moderate/heavy rain, and wind
 * 
 * 
 * @author Lucas Davey
 * @version Spring 2026
 */

public class WeatherIcon implements Runnable {
    private JPanel panel;
    private String weatherCondition;

    //cloud colors
    private final Color CLOUD_WHITE = new Color(230,230,240);
    private final Color CLOUD_GRAY = new Color(140,140,160);
    private final Color CLOUD_DARK = new Color(70,70,90);

    //rain and lightning colors
    private final Color RAIN_BLUE = new Color(80,140,200);
    private final Color LIGHTNING_BOLT = new Color(255,240,60);

    public WeatherIcon(String condition){
        this.weatherCondition = condition;
    }

    @Override
    public void run(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Weather: " + weatherCondition);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    private void sunnyDay(){

    }

    private void partlyCloudy(){

    }

    private void cloudyDay(){

    }

    private void rainyDay(){

    }

    private void thunderstorm(){

    }

    private void windyDay(){

    }

    public static void main(String args[]){
        
    }
   

}
