package lilee.hd.anotterredditapp.reddit;

import java.util.Date;

public class DateConverter {
    @androidx.room.TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @androidx.room.TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
