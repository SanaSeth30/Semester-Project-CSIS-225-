import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Implementation of WeatherData that fetches and stores a 5-day forecast.
 * This class uses a single API call with daily timesteps for efficiency.
 * * @author Patrick McFee
 * @version Spring 2026
 */
public class WeekData implements WeatherData {
    /** The start date associated with this data. */
    private ZonedDateTime day;
    /** Daily average temperature records. */
    private ArrayList<Double> dailyTemps;
    /** Daily average wind speed records. */
    private ArrayList<Double> dailyWindSpeed;
    /** Daily precipitation type codes. */
    private ArrayList<Integer> dailyPrecipitation;
    /** Daily average precipitation probability percentages. */
    private ArrayList<Integer> dailyPrecipitaitonProbability;
    /** Daily average cloud cover percentages. */
    private ArrayList<Double> dailyCloudCoverPrecentage;
    /** Daily average humidity percentages. */
    private ArrayList<Integer> dailyHumidity;
    /** Coordinates of location. */
    private String coordinates;

    public WeekData(ZonedDateTime startDay, String coordinates) {
        // Normalize time to the start of the day
        this.day = startDay.withHour(0).withMinute(0).withSecond(0).withNano(0);
        this.coordinates = coordinates;
        
        // Initializing lists to store the 5 days of results
        this.dailyTemps = new ArrayList<Double>();
        this.dailyWindSpeed = new ArrayList<Double>();
        this.dailyPrecipitation = new ArrayList<Integer>();
        this.dailyPrecipitaitonProbability = new ArrayList<Integer>();
        this.dailyCloudCoverPrecentage = new ArrayList<Double>();
        this.dailyHumidity = new ArrayList<Integer>();
    }

    /**
     * Fetches 5 days of data in a single request using daily timesteps.
     */
    @Override
    public void populateData() {
        String apiKey = "JPNyTSLdw7EZQ5nnsWlSgzPLgcSeOIKo";
        ZonedDateTime start = day;
        ZonedDateTime end = start.plusDays(5);

        String[] searchKeys = {"temperature", "windSpeed", "precipitationType", "precipitationProbability", "cloudCover", "humidity"};

        try {
            String startTime = URLEncoder.encode(start.toInstant().toString(), StandardCharsets.UTF_8);
            String endTime = URLEncoder.encode(end.toInstant().toString(), StandardCharsets.UTF_8);
            String urlString = "https://api.tomorrow.io/v4/timelines?"
                    + "location=" + coordinates
                    + "&fields=temperature,windSpeed,precipitationType,precipitationProbability,cloudCover,humidity"
                    + "&units=imperial"
                    + "&timesteps=1d"
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
                        String valStr = fullJson.substring(startVal, endVal).trim();
                        if (i == 0) {
                            dailyTemps.add(Double.parseDouble(valStr));
                        } else if (i == 1) {
                            dailyWindSpeed.add(Double.parseDouble(valStr));
                        } else if (i == 2) {
                            dailyPrecipitation.add(Integer.parseInt(valStr));
                        } else if (i == 3) {
                            dailyPrecipitaitonProbability.add((int) Double.parseDouble(valStr));
                        } else if (i == 4) {
                            dailyCloudCoverPrecentage.add(Double.parseDouble(valStr));
                        } else if (i == 5) {
                            dailyHumidity.add((int) Double.parseDouble(valStr));
                        }
                        lastIdx = endVal;
                    }
                }
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getTemperature(int dayIndex) {
        return dailyTemps.get(dayIndex);
    }

    @Override
    public double getWindSpeed(int day) {
        return dailyWindSpeed.get(day);
    }

    @Override
    public String getPrecipitationType(int day) {
        int precip = dailyPrecipitation.get(day);
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

    @Override
    public int getPrecipitationProbability(int day) {
        return dailyPrecipitaitonProbability.get(day);
    }

    @Override
    public double getCloudCoverPercentage(int day) {
        return dailyCloudCoverPrecentage.get(day);
    }

    @Override
    public int getHumitity(int day) {
        return dailyHumidity.get(day);
    }
}