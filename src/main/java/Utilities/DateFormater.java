package Utilities;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFormater {

    public static String formatDate() {
        Faker faker = new Faker();
        // Generate a random future date
        Date randomDate = faker.date().future(365, TimeUnit.DAYS); // Adjust the range as needed
        // Format the date as a string in the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return formatter.format(randomDate);
    }
}
