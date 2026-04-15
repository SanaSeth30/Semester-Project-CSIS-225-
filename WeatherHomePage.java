import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
/**
 * This program will display the weather home page
 * this will display city one day and 7 day forcast
 * 
 * 
 * @author Sanju Arikatla
 * @version Spring 2026
 */

public class WeatherHomePage extends JFrame {

    public WeatherHomePage() {
        setTitle("Weather Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        //this is where we will be able to get the selection for the city and display it
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel cityLabel = new JLabel("Select City: ");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 24));
        String[] cities = {"New York City", "Buffalo", "Yonkers", "Rochester", "Syracuse", "Albany", "New Rochelle"};
        JComboBox<String> cityDropdown = new JComboBox<>(cities);

        topPanel.add(cityLabel);
        topPanel.add(cityDropdown);
        add(topPanel, BorderLayout.NORTH);

        // Main split panel so on the  left we have our box that displayes one day forecast right we have seven day forecast
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // One day forecast panel with thick border and title
        OneDayForecast oneDayPanel = new OneDayForecast();
        oneDayPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "One Day Forecast"));

        // Seven day forecast panel with thick border and title
        FiveDayForecast fiveDayPanel = new FiveDayForecast();
        fiveDayPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "Five Day Forecast"));

        splitPane.setLeftComponent(oneDayPanel);
        splitPane.setRightComponent(fiveDayPanel);
        splitPane.setDividerLocation(4500);
        add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WeatherHomePage homePage = new WeatherHomePage();
                homePage.setVisible(true);
            }
        });
    }
}
