package bs.utils;

import java.util.Date;

public class Utils {

    public static String toDateTimeString(long stamp) {
        return new Date(stamp).toLocaleString();
    }
}
