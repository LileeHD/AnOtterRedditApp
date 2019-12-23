package lilee.hd.anotterredditapp.util;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.post.Post;

public class ConverterUtil {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
    }

