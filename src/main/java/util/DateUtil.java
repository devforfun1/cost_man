package util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static LocalDate ConvertDate(Date date){

        return Instant.
                ofEpochMilli(date.getTime()).
                atZone(ZoneId.systemDefault()).
                toLocalDate();
    }

    public static String ConvertDate(LocalDate ld) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ld.format(formatter);
    }

    public static String ConvertDateTime(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return ldt.format(formatter);
    }

    public static LocalDate GetLastDateOfCurrentMonth() {

        LocalDate current = LocalDate.now();

        return LocalDate.of(current.getYear(), current.getMonth(), current.lengthOfMonth());
    }

    public static LocalDate GetFirstDateOfCurrentMonth() {

        LocalDate current = LocalDate.now();

        return LocalDate.of(current.getYear(), current.getMonth(), 1);
    }

    public static LocalDate GetFirstDateOfMonth(Month month) {

        LocalDate current = LocalDate.now();

        return LocalDate.of(current.getYear(), month, 1);
    }
}
