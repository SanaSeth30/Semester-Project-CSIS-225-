import java.time.ZonedDateTime;

/**
 * Interface for retrieving atmospheric data from remote weather services.
 * @author Patrick McFee
 * @version Spring 2026
 */
public interface WeatherData {
    
    /**
     * Retrieves the temperature at a specific time and location.
     * @param dateTime The specific date and time for the forecast.
     * @param zipCode  The postal code for the location.
     * @return Temperature in Fahrenheit.
     */
    double getTemperature(ZonedDateTime dateTime);

    /**
     * Retrieves the type of precipitation (e.g., "Rain", "Snow", "None").
     * @param dateTime The specific date and time.
     * @return A string representing the precipitation type.
     */
    String getPrecipitationType(ZonedDateTime dateTime);

    /**
     * Retrieves the wind speed.
     * @param dateTime The specific date and time.
     * @return Wind speed in miles per hour.
     */
    double getWindSpeed(ZonedDateTime dateTime);

    /**
     * Retrieves the probability of precipitation.
     * @param dateTime The specific date and time.
     * @return Percentage chance (0-100).
     */
    int getPrecipitationProbability(ZonedDateTime dateTime);
}