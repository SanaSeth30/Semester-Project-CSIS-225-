import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of WeatherData that fetches and stores daily weather metrics.
 * @author Patrick McFee
 * @version Spring 2026
 */
public class DayData implements WeatherData {
    /** The date associated with this data. */
    private ZonedDateTime day;
    /** Properly formatted start time string. */
    private String Start;
    /** Properly formatted end time string. */
    private String end;
    /** Hourly temperature records. */
    private ArrayList<Double> hourlyTemps;
    /** Hourly wind speed records. */
    private ArrayList<Double> hourlyWindSpeed;
    /** Hourly precipitation type codes. */
    private ArrayList<Integer> hourlyPrecipitation;
    /** Hourly precipitation probability percentages. */
    private ArrayList<Integer> hourlyPrecipitaitonProbability;
    /** Hourly cloud cover percentages. */
    private ArrayList<Integer> hourlyCloudCoverPrecentage;
    /** Hourly humidity percentages. */
    private ArrayList<Integer> hourlyHumidity;

    public DayData(ZonedDateTime day, String coordinates) {
        this.day = day;
        hourlyPrecipitation = new ArrayList<Integer>();
        hourlyTemps = new ArrayList<Double>();
        hourlyWindSpeed = new ArrayList<Double>();
        hourlyPrecipitaitonProbability = new ArrayList<Integer>();
        hourlyCloudCoverPrecentage = new ArrayList<Integer>();
        hourlyHumidity = new ArrayList<Integer>();
    }

    /**
     * Fetches and populates the weather data from the underlying data source.
     */
    @Override
    public void populateData() {
        String apiKey = "JPNyTSLdw7EZQ5nnsWlSgzPLgcSeOIKo";
        ZonedDateTime start = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime end = start.plusHours(23);

        String[] searchKeys = {"temperature", "windSpeed", "precipitationType", "precipitationProbability", "cloudCover", "humidity"};

        try {
            String startTime = URLEncoder.encode(start.toInstant().toString(), StandardCharsets.UTF_8);
            String endTime = URLEncoder.encode(end.toInstant().toString(), StandardCharsets.UTF_8);

            String urlString = "https://api.tomorrow.io/v4/timelines?"
                    + "location=43.2994,-74.2179"
                    + "&fields=temperature,windSpeed,precipitationType,precipitationProbability,cloudCover,humidity"
                    + "&units=imperial"
                    + "&timesteps=1h"
                    + "&startTime=" + startTime
                    + "&endTime=" + endTime
                    + "&apikey=" + apiKey;

            URL apiUrl = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                StringBuilder responseBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                }

                String fullJson = responseBuilder.toString();

                for (int i = 0; i < 6; i++) {
                    String searchKey = "\"" + searchKeys[i] + "\":";
                    int lastIdx = 0;

                    while ((lastIdx = fullJson.indexOf(searchKey, lastIdx)) != -1) {
                        int startVal = lastIdx + searchKey.length();
                        int endVal = fullJson.indexOf(",", startVal);
                        if (endVal == -1 || endVal > fullJson.indexOf("}", startVal)) {
                            endVal = fullJson.indexOf("}", startVal);
                        }

                        String tempStr = fullJson.substring(startVal, endVal).trim();

                        if (i == 0) {
                            hourlyTemps.add(Double.parseDouble(tempStr));
                        } else if (i == 1) {
                            hourlyWindSpeed.add(Double.parseDouble(tempStr));
                        } else if (i == 2) {
                            hourlyPrecipitation.add(Integer.parseInt(tempStr));
                        } else if (i == 3) {
                            hourlyPrecipitaitonProbability.add(Integer.parseInt(tempStr));
                        } else if (i == 4) {
                            hourlyCloudCoverPrecentage.add(Integer.parseInt(tempStr));
                        } else if (i == 5) {
                            hourlyHumidity.add(Integer.parseInt(tempStr));
                        }
                        lastIdx = endVal;
                    }
                }
            } else {
                System.out.println("Server Error: " + connection.getResponseCode());
            }
            connection.disconnect();

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the temperature at a specific time of day.
     * @param hour The hour of the day.
     * @return The temperature as a double.
     */
    @Override
    public double getTemperature(int hour) {
        return hourlyTemps.get(hour);
    }

    /**
     * Retrieves the wind speed at the specified time.
     * @param hour The hour of the day.
     * @return Wind speed in miles per hour.
     */
    @Override
    public double getWindSpeed(int hour) {
        return hourlyWindSpeed.get(hour);
    }

    /**
     * Retrieves the type of precipitation occurring at the specified time.
     * @param hour The hour of the day.
     * @return A string representing the precipitation type.
     */
    @Override
    public String getPrecipitationType(int hour) {
        int precip = hourlyPrecipitation.get(hour);
        if (precip == 0) {
            return "no precipitation";
        } else if (precip == 1) {
            return "rain";
        } else if (precip == 2) {
            return "snow";
        } else if (precip == 3) {
            return "freezing rain";
        } else {
            return "sleet";
        }
    }

    /**
     * Retrieves the probability of precipitation at the specified time.
     * @param hour The hour of the day.
     * @return The percentage chance of precipitation (0-100).
     */
    @Override
    public int getPrecipitationProbability(int hour) {
        return hourlyPrecipitaitonProbability.get(hour);
    }

    /**
     * Retrieves the percentage of cloud cover at the specified time.
     * @param hour The hour of the day.
     * @return The cloud cover percentage (0-100).
     */
    @Override
    public int getCloudCoverPercentage(int hour) {
        return hourlyCloudCoverPrecentage.get(hour);
    }

    /**
     * Retrieves the humidity percentage at the specified time.
     * @param hour The hour of the day.
     * @return The humidity percentage (0-100).
     */
    @Override
    public int getHumitity(int hour) {
        return hourlyHumidity.get(hour);
    }
}