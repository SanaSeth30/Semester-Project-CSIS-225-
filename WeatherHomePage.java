import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.time.ZonedDateTime;

/**
 * displays one day and 5 day page
 * 
 * @author Sanju Arikatla
 * @version Spring 2026
 */
public class WeatherHomePage extends JFrame {

    private JComboBox<String> cityDropdown;

    private OneDayForecast oneDayPanel;
    private FiveDayForecast fiveDayPanel;

    public WeatherHomePage() {

        setTitle("Weather Home Page");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel();

        JLabel cityLabel = new JLabel("Select City:");

        String[] cities = {
            "New York City", "Buffalo", "Yonkers",
            "Rochester", "Syracuse", "Albany", "New Rochelle"
        };

        cityDropdown = new JComboBox<>(cities);

        topPanel.add(cityLabel);
        topPanel.add(cityDropdown);

        add(topPanel, BorderLayout.NORTH);

        // ---------- ONE DAY PANEL ----------
        oneDayPanel = new OneDayForecast();

        JPanel oneDayWrapper = new JPanel(new BorderLayout());
        oneDayWrapper.setBorder(
            new TitledBorder(new LineBorder(Color.BLACK), "One Day Forecast")
        );

        oneDayWrapper.add(oneDayPanel, BorderLayout.CENTER);

        // ---------- FIVE DAY PANEL ----------
        fiveDayPanel = new FiveDayForecast();

        JPanel fiveDayWrapper = new JPanel(new BorderLayout());
        fiveDayWrapper.setBorder(
            new TitledBorder(new LineBorder(Color.BLACK), "Five Day Forecast")
        );

        fiveDayWrapper.add(fiveDayPanel, BorderLayout.CENTER);

        // ---------- SPLIT SCREEN ----------
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            oneDayWrapper,
            fiveDayWrapper
        );

        splitPane.setDividerLocation(400);

        add(splitPane, BorderLayout.CENTER);

        // DROPDOWN ACTION 
        cityDropdown.addActionListener(e -> {

            String city = (String) cityDropdown.getSelectedItem();
            String coords = getCoordinates(city);

            // get weather data
            DayData today = new DayData(ZonedDateTime.now(), coords);
            today.populateData();

            // update panels (IMPORTANT: these methods must exist)
            oneDayPanel.updateData(today);
            fiveDayPanel.updateData(coords);
        });
    }

    // maps city to coordinates
    private String getCoordinates(String city) {
        if (city.equals("New York City")) return "40.7128,-74.0060";
        if (city.equals("Buffalo")) return "42.8864,-78.8784";
        if (city.equals("Yonkers")) return "40.9312,-73.8988";
        if (city.equals("Rochester")) return "43.1566,-77.6088";
        if (city.equals("Syracuse")) return "43.0481,-76.1474";
        if (city.equals("Albany")) return "42.6526,-73.7562";
        if (city.equals("New Rochelle")) return "40.9115,-73.7824";

        return "40.7128,-74.0060";
    }

    public static void main(String[] args) {
        new WeatherHomePage().setVisible(true);
    }
}