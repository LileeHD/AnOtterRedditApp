//package lilee.hd.anotterredditapp.database;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import java.util.List;
//
//import lilee.hd.anotterredditapp.model.subreddit.Subreddit;
//
//@Dao
//public interface SubredditDao {
//
//    @Query("SELECT * FROM subreddit_table ORDER BY sort DESC")
//    LiveData<List<Subreddit>> getAllsubreddits();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(Subreddit subreddit);
//
//    @Delete
//    void delete(Subreddit subreddit);
//
//    @Query("SELECT * from subreddit_table WHERE display_name = :name COLLATE NOCASE LIMIT 1")
//    LiveData<Subreddit> getSubredditLiveDataByName(String name);
//
//}
