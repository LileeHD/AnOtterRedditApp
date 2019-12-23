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

        private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        @TypeConverter
//        public static Date fromTimestamp(String value) {
//            if (value != null) {
//                try {
//                    TimeZone timeZone = TimeZone.getTimeZone("IST");
//                    df.setTimeZone(timeZone);
//                    return df.parse(value);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            } else {
//                return null;
//            }
//        }
//
//
//        @TypeConverter
//        public static String dateToTimestamp(Date value) {
//            TimeZone timeZone = TimeZone.getTimeZone("IST");
//            df.setTimeZone(timeZone);
//            return value == null ? null : df.format(value);
//        }
//
//    private static final int SECOND = 1000;
//
//    @Nullable
//    static List<Post> toPosts(Feed feed) {
//        List<Children> postData = feed != null ? feed.getData().getChildren() : null;
//        if (postData == null || postData.size() == 0) {
//            return null;
//        }
//
//        List<Post> posts = new ArrayList<>();
//        for (Children feedElem : postData) {
//            posts.add(toPost(feedElem));
//        }
//        return posts;
//    }
//
//    private static Post toPost(Children children) {
//        Post post = children.getData();
//        long date = post.getDate() * SECOND;
//        post.setDate(date);
////        post.setOrder(date);
//        return post;
//    }

    public static void convertTime(String date){

    }
    public static void main(String[] args) {
        Long epochTime = System.currentTimeMillis(); // Gets milliseconds passed since epoch(1970)

        Date date = new Date(); // Same thing, but represents date object for helpful utilities

        System.out.println(epochTime);
        System.out.println(date);
        System.out.println(date.getTime()); // This prints the time in milliseconds
    }
    }

