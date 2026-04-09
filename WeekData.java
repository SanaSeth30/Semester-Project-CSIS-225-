import java.time.ZonedDateTime;
import java.util.ArrayList;

public class WeekData extends DayData {
     /** The date associated with this data. */
    private ZonedDateTime day;
    /** Daily high temperature records. */
    private ArrayList<Double> dailyHighTemps;
    /** Daily low temperature records. */
    private ArrayList<Double> dailyLowTemps;
    /** Daily average wind speed records. */
    private ArrayList<Double> dailyAverageWindSpeed;
    /** daily precipitation type codes. */
    private ArrayList<Integer> dailyPrecipitation;
    /** Hourly precipitation probability percentages. */
    private ArrayList<Integer> dailyAveragePrecipitaitonProbability;
    /** Hourly cloud cover percentages. */
    private ArrayList<Double> dailyAverageCloudCoverPrecentage;
    /** Hourly humidity percentages. */
    private ArrayList<Integer> dailyAverageHumidity;
    /** Coordinates of location */
    private String coordinates; 
}
