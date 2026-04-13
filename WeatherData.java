import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Interface for retrieving weather data
 * 
 * @author Patrick McFee
 * @version Spring 2026
 */
public interface WeatherData {

    /**
     * Retrieves the temperature at a specific time of day.
     * 
     * @param when The hour of the day.
     * @return The temperature as a double.
     */
    double getTemperature(int when);

    /**
     * Retrieves the type of precipitation occurring at the specified time.
     * 
     * @param when The hour of the day.
     * @return A string representing the precipitation type.
     */
    String getPrecipitationType(int when);

    /**
     * Retrieves the wind speed at the specified time.
     * 
     * @param when The hour of the day.
     * @return Wind speed in miles per hour.
     */
    double getWindSpeed(int when);

    /**
     * Retrieves the probability of precipitation at the specified time.
     * 
     * @param when The hour of the day.
     * @return The percentage chance of precipitation (0-100).
     */
    int getPrecipitationProbability(int when);

    /**
     * Fetches and populates the weather data from the underlying data source.
     */
    void populateData();

    /**
     * Retrieves the percentage of cloud cover at the specified time.
     * 
     * @param when The hour of the day.
     * @return The cloud cover percentage (0-100).
     */
    double getCloudCoverPercentage(int when);

    /**
     * Retrieves the humidity percentage at the specified time.
     * 
     * @param when The hour of the day.
     * @return The humidity percentage (0-100).
     */
    int getHumitity(int when);

    /**
     * Retrieves the low tempature for the day
     * 
     * @return The low tempature for the day
     */

    //double getDailyLowestTemp();

    /**
     * Retrieves the high tempature for the day
     * @return the high tempature for the day
     */
    //double getDailyHighestTemp();
}
