package utils;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class DataGenerator
{
    private static Faker faker = new Faker();
    private static DataGenerator inst = null;


    private DataGenerator()
    {

    }

    public static DataGenerator getInstance()
    {
        if (inst == null)
        {
            inst = new DataGenerator();
        }
        return inst;
    }

    public String firstName()
    {
        return faker.name().firstName();
    }

    public String lastName()
    {
        return faker.name().lastName();
    }

    public String gender()
    {
        return faker.demographic().sex();
    }

    public String uuid()
    {
        return faker.internet().uuid();
    }

    public Date sameDayMinusYears(int age)
    {
        return Date.from(Instant.from(LocalDateTime.now().minusYears(age)));
    }

    public String getTodaysDate()
    {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String addDaysToTodaysDate(int days)
    {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(days);
        return futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));    }
}
