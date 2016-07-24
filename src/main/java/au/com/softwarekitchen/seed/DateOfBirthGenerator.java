package au.com.softwarekitchen.seed;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class DateOfBirthGenerator {

    private static final Random deterministicRandom = new Random(76367086L);

    private static final Instant startDate = Instant.from(ZonedDateTime.of(1910, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()));
    private static final Instant endDate = Instant.from(ZonedDateTime.of(1995, 12, 31, 23, 59, 59, 0, ZoneId.systemDefault()));
    
    public static LocalDate next() {

        final long range = (endDate.toEpochMilli() - startDate.toEpochMilli());

        final long dobAsLong = startDate.toEpochMilli() + (long)(deterministicRandom.nextDouble() * range);
        
        final Instant dobInstant = Instant.ofEpochMilli(dobAsLong);

        final ZonedDateTime dob = ZonedDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        
        return dob.toLocalDate();
    }


}
