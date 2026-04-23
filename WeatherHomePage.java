import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
/**
 * This is the main home page for the weather app.
 * It lets the user pick a city and then shows:
 *  one day forecast
 * -five day forecast
 *
 * The panels update automatically when the city changes.
 *
 * @author Sanju Arikatla
 * @version Spring 2026
 */

public class WeatherHomePage extends JFrame {

    private JComboBox<String> cityDropdown;
    private JSplitPane splitPane;

    // these are the two main panels on the screen
    private OneDayForecast oneDayPanel;
    private FiveDayForecast fiveDayPanel;

    public WeatherHomePage() {

        setTitle("Weather Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

    
        // TOP PANEL CITY SELECTOR
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Select City"));

        // list of cities user can choose from
        String[] cities = {  "Albany", "New York City", "Buffalo", "Yonkers", "Rochester", "Syracuse", "New Rochelle"  };

        //dropdown menu for cities
        cityDropdown = new JComboBox<>(cities);

        //whenever user picks a new city, refresh the weather panels
        cityDropdown.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        refreshPanels();
    }
});
        topPanel.add(cityDropdown);
        add(topPanel, BorderLayout.NORTH);

    
        //INITIAL WEATHER PANELS
        //get the default selected city when program starts
        String city = (String) cityDropdown.getSelectedItem();

        //create both forecast panels
        oneDayPanel = new OneDayForecast(city);
        fiveDayPanel = new FiveDayForecast();

        //split screen left is the  1 day and the  right is the 5 day
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, oneDayPanel, fiveDayPanel);
        splitPane.setDividerLocation(450);

        add(splitPane, BorderLayout.CENTER);
    }

    /**
     * This method runs every time the user changes the city.
     */
    private void refreshPanels() {

        //get newly selected city
        String city = (String) cityDropdown.getSelectedItem();

        //recreate panels with updated city info
        oneDayPanel = new OneDayForecast(city);
        fiveDayPanel = new FiveDayForecast();

        //replace panels in the split view
        splitPane.setLeftComponent(oneDayPanel);
        splitPane.setRightComponent(fiveDayPanel);

        // refresh UI so changes actually show up
        splitPane.revalidate();
        splitPane.repaint();
    }


    //MAIN METHOD
 public static void main(String[] args) {
    WeatherHomePage page = new WeatherHomePage();
    page.setVisible(true);
}
}