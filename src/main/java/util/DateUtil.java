package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String ConvertDate(LocalDate ld) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ld.format(formatter);
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
