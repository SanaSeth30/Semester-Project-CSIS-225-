import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.time.ZonedDateTime;

/**
 * This program will display a weather home page where the user can select a city
 * and view both a one day forecast and a five day forecast.
 * It also shows weather icons sunny, cloudy, rainy, windy, etc.
 * next to the current weather using animation.
 *
 *
 * @author Sanju Arikatla
 * @version Spring 2026
 */
public class WeatherHomePage extends JFrame {

    // dropdown for selecting different cities
    private JComboBox<String> cityDropdown;

    // panels that display weather info
    private OneDayForecast oneDayPanel;
    private FiveDayForecast fiveDayPanel;

    // weather icon that shows next to the one day forecast
    private WeatherIcon weatherIcon;

    public WeatherHomePage() {

        // setting up main window
        setTitle("Weather Home Page");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // top section where city is selected
        JPanel topPanel = new JPanel();

        JLabel cityLabel = new JLabel("Select City:");

        String[] cities = { "New York City", "Buffalo", "Yonkers", "Rochester", "Syracuse", "Albany", "New Rochelle"};

        cityDropdown = new JComboBox<>(cities);

        topPanel.add(cityLabel);
        topPanel.add(cityDropdown);

        add(topPanel, BorderLayout.NORTH);

        // creating forecast panels
        oneDayPanel = new OneDayForecast();
        fiveDayPanel = new FiveDayForecast();

        // make sure icon shows next to weather info 
        oneDayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // wrapper for one day forecast adds title + border
        JPanel oneDayWrapper = new JPanel(new BorderLayout());
        oneDayWrapper.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "One Day Forecast"));
        oneDayWrapper.add(oneDayPanel);

        // wrapper for five day forecast
        JPanel fiveDayWrapper = new JPanel(new BorderLayout());
        fiveDayWrapper.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Five Day Forecast"));
        fiveDayWrapper.add(fiveDayPanel);

        // split screen between both forecast panels
        JSplitPane splitPane = new JSplitPane(
             JSplitPane.HORIZONTAL_SPLIT,
                oneDayWrapper,
                fiveDayWrapper
        );

        splitPane.setDividerLocation(400);
        add(splitPane, BorderLayout.CENTER);

        //when user changes city update weather
        cityDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWeather();
            }
        });

        //load default city 
        updateWeather();
    }

    private void updateWeather() {

        String city = (String) cityDropdown.getSelectedItem();
        String coordinates = getCoordinates(city);

        // get weather data for today
        DayData today = new DayData(ZonedDateTime.now(), coords);
        today.populateData();

        // update forecast panels
        oneDayPanel.updateData(today);
        fiveDayPanel.updateData(coordinates);


        // WEATHER ICON SECTION

        //remove old icon so they don't stack on top of each other
        if (weatherIcon != null) {
            oneDayPanel.remove(weatherIcon);
        }

        //determine condition manually since DayData has no getCondition
        String condition;
        double cloud = today.getCloudCoverPercentage(12); // midday
        String precip = today.getPrecipitationType(12);

        if (precip.equals("rain")) {
            condition = "rainy";
        } else if (cloud > 70) {
            condition = "cloudy";
        } else if (cloud > 40) {
            condition = "partly cloudy";
        } else {
            condition = "sunny";
        }

        //create icon based on weather condition
        weatherIcon = new WeatherIcon(condition);
        weatherIcon.startAnimation();

        //add icon into the one day forecast panel
        oneDayPanel.add(weatherIcon);

        //refresh  so icon appears immediately
        oneDayPanel.revalidate();
        oneDayPanel.repaint();
    }

    //converts city names into latitude and longitude coordinates so we can get locations of each
    private String getCoordinates(String city) {

        if (city.equals("New York City")) return "40.7128,-74.0060";
        if (city.equals("Buffalo")) return "42.8864,-78.8784";
        if (city.equals("Yonkers")) return "40.9312,-73.8988";
        if (city.equals("Rochester")) return "43.1566,-77.6088";
        if (city.equals("Syracuse")) return "43.0481,-76.1474";
        if (city.equals("Albany")) return "42.6526,-73.7562";
        if (city.equals("New Rochelle")) return "40.9115,-73.7824";

        //default  city
        return "40.7128,-74.0060";
    }

    // runs the program
    public static void main(String[] args) {
        new WeatherHomePage().setVisible(true);
    }
}