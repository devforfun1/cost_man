package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String ConvertDate(LocalDate ld) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ld.format(formatter);
    }

    public static LocalDate GetLastDayInMonth(){

       LocalDate current = LocalDate.now();


        return LocalDate.of(current.getYear(),current.getMonth(),current.lengthOfMonth());
    }
}
