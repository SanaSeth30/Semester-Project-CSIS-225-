import javax.swing.*;

public class TestWeatherIcon {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Weather Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        WeatherIcon icon = new WeatherIcon("rainy");
        frame.add(icon);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
