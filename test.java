
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * This file is used for the testing of the DayData class
 * 
 * @author Patrick McFee
 * @version Spring 2026
 */
public class test {
/**
 * this main method is used for testing of the DayData method
 * 
 * FEEL FREE TO EDIT AND CHANGE WHATEVER YOU LIKE IN THIS CLASS
 * SO YOU UNDERSTAND HOW TO IMPLEMENT THE DATA INTO THE FORECAST
 * 
 * @param args
 */
    public static void main(String[] args) {
        
        System.out.println("Data for Albany, NY");
        DayData today = new DayData(ZonedDateTime.now(), "43.2994,-74.2179");
        today.populateData();
        System.out.println(
                today.getPrecipitationType(0) + "at 1am with a probability of " + today.getPrecipitationProbability(0)
                        + "%" + " and a cloud coverage of " + today.getCloudCoverPercentage(0) + "%");
        System.out.println("The daily high tempature is " + today.getDailyHighestTemp() + " and the low is "
                + today.getDailyLowestTemp() + " and the UV index is " + today.getUvIndex(12));
        System.out.println(
                today.getWindSpeed(0) + "mph is the wind speed, " + today.getHumitity(0)
             + "% is the humidity");

    }

}
