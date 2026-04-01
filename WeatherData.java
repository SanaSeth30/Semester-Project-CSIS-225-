import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Interface for retrieving weather data
 * @author Patrick McFee
 * @version Spring 2026
 */
public interface WeatherData {
    
    /**
     * Retrieves the temperature
     * @return An ArrayList of tempatures thoughout the day
     */
    ArrayList<Double> getTemperature();

    /**
     * Retrieves the type of precipitation
     * @return A string representing the precipitation type.
     */
    String getPrecipitationType();

    /**
     * Retrieves the wind speed.
     * @return Wind speed in miles per hour.
     */
    double getWindSpeed();

    /**
     * Retrieves the probability of precipitation.
     * @return Percentage chance (0-100).
     */
    int getPrecipitationProbability();
}