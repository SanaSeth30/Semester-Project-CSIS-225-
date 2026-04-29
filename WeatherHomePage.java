import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * This class represents the main Weather Home Page GUI.
 * It allows users to:
 * Select a city
 * View a 1-day forecast
 * View a 5-day forecast
 * See weather safety tips
 * See a daily motivational quote
 *
 *
 * @author Sanju
 * @version Spring 2026
 */
public class WeatherHomePage extends JFrame {

    // dropdown for selecting city
    private JComboBox<String> cityDropdown;

    // split pane holding 1-day and 5-day forecast
    private JSplitPane splitPane;

    // forecast panels
    private OneDayForecast oneDayPanel;
    private FiveDayForecast fiveDayPanel;

    // bottom section
    private JPanel bottomPanel;
    // safety tip UI components
    private JPanel safetyPanel;
    private JLabel safetyLabel;
    // quote UI components
    private JPanel quotePanel;
    private JLabel quoteLabel;

    /**
     * Constructor builds the entire Weather Home Page UI.
     * Initializes all panels and loads default city data.
     */
    public WeatherHomePage() {

        // window setup
        setTitle("Weather Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOP PANEL CITY SELECTOR
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Select City"));

        String[] cities = {
                "Albany", "New York City", "Buffalo",
                "Yonkers", "Rochester", "Syracuse", "New Rochelle"
        };

        cityDropdown = new JComboBox<>(cities);

        // get the currently selected city (default value)
        String city = (String) cityDropdown.getSelectedItem();

        // update UI whenever city changes
        cityDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                refreshPanels();
            }
        });

        topPanel.add(cityDropdown);
        add(topPanel, BorderLayout.NORTH);

        // create 1-day forecast
        oneDayPanel = new OneDayForecast(city);

        // get coordinates for API
        String coordinates = getCoordinates(city);

        // create 5-day forecast and load data
        fiveDayPanel = new FiveDayForecast();
        fiveDayPanel.updateData(coordinates, city);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, oneDayPanel, fiveDayPanel);
        splitPane.setDividerLocation(450);
        add(splitPane, BorderLayout.CENTER);

        // BOTTOM PANEL SAFETY + QUOTE
        bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.setBackground(Color.WHITE);

        // SAFETY PANEL
        safetyPanel = new JPanel();
        safetyPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), " Safety Tips"));

        safetyLabel = new JLabel("Loading safety tips...", JLabel.CENTER);
        safetyLabel.setFont(new Font("Georgia", Font.PLAIN, 14));

        safetyPanel.add(safetyLabel);

        // QUOTE PANEL
        quotePanel = new JPanel();
        quotePanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Daily Quote"));

        quoteLabel = new JLabel("Loading quote...", JLabel.CENTER);
        quoteLabel.setFont(new Font("Georgia", Font.PLAIN, 14));

        quotePanel.add(quoteLabel);

        bottomPanel.add(safetyPanel);
        bottomPanel.add(quotePanel);

        add(bottomPanel, BorderLayout.SOUTH);

        // initial bottom updates
        updateSafetyTip(city);
        updateQuote();
    }
    /**
     * Refreshes both forecast panels when a new city is selected.
     */
    private void refreshPanels() {

        String city = (String) cityDropdown.getSelectedItem();

        // rebuild 1-day forecast
        oneDayPanel = new OneDayForecast(city);

        // rebuild 5-day forecast
        String coordinates = getCoordinates(city);
        fiveDayPanel = new FiveDayForecast();
        fiveDayPanel.updateData(coordinates, city);

        // update split pane components
        splitPane.setLeftComponent(oneDayPanel);
        splitPane.setRightComponent(fiveDayPanel);

        splitPane.revalidate();
        splitPane.repaint();

        updateSafetyTip(city);
        updateQuote();
    }

    /**
     * Generates a weather safety tip 
     */
    private void updateSafetyTip(String city) {

        try {
            String coordinates = getCoordinates(city);

            DayData data = new DayData(ZonedDateTime.now(), coordinates);
            data.populateData();

            double temp = data.getTemperature(12);
            String precip = data.getPrecipitationType(12);

            String tip;

            if (temp <= 32) {
                tip = "Cold weather: Wear gloves and stay warm.";
            }
            else {
                if (temp >= 90) {
                    tip = "Hot weather: Drink water and avoid going outside too long.";
                }
                else {
                    if (precip.equals("rain")) {
                        tip = "Rainy weather: Drive carefully.";
                    }
                    else {
                        if (precip.equals("snow")) {
                            tip = "Snowy weather: Wear warm clothes and be careful outside.";
                        }
                        else {
                            tip = "Weather is nice. Have a great day!";
                        }
                    }
                }
            }

            safetyLabel.setText(tip);

        } catch (Exception e) {
            safetyLabel.setText("Safety tips unavailable.");
        }
    }

    /**
     * Displays a random motivational weather related quote.
     */
    private void updateQuote() {

      String[] quotes = {
    "Stay positive every day.",
    "A new forecast brings new chances.",
    "Prepare, don’t panic!",
    "Weather changes, so can your day.",
    "Every day is a fresh start.",
    "Storms don’t last forever.",
    "Make today count."
};

        int index = (int) (Math.random() * quotes.length);
        quoteLabel.setText(quotes[index]);
    }

    /**
     * Converts city names into latitude/longitude coordinates.
     * Used for API requests.
     */
    private String getCoordinates(String city) {

        if (city.equals("Albany"))
            return "42.6526,-73.7562";
        if (city.equals("New York City"))
            return "40.7128,-74.0060";
        if (city.equals("Buffalo"))
            return "42.8864,-78.8784";
        if (city.equals("Yonkers"))
            return "40.9312,-73.8988";
        if (city.equals("Rochester"))
            return "43.1566,-77.6088";
        if (city.equals("Syracuse"))
            return "43.0481,-76.1474";
        if (city.equals("New Rochelle"))
            return "40.9115,-73.7824";

        return "42.6526,-73.7562";
    }

    /**
     * Main method used to launch the Weather Home Page application.
     */
    public static void main(String[] args) {
        new WeatherHomePage().setVisible(true);
    }
}