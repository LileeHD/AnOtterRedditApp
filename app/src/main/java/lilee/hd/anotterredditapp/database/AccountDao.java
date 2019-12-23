package lilee.hd.anotterredditapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Query("DELETE FROM account_table")
    void deleteCurrentAccount();

    @Query("SELECT * FROM account_table LIMIT 1")
    Account getCurrentAccount();

    @Query("SELECT * FROM account_table WHERE username = :username COLLATE NOCASE LIMIT 1")
    LiveData<Account> getAccountLiveData(String username);

    @Query("SELECT * FROM account_table WHERE username = :username COLLATE NOCASE LIMIT 1")
    Account getAccountData(String username);

    @Query("SELECT * FROM account_table LIMIT 1")
    LiveData<Account> getCurrentAccountLiveData();

//    @Query("UPDATE account_table SET profile_image_url = :profileImageUrl, banner_image_url = :bannerImageUrl WHERE username = :username")
//    void updateAccountInfo(String username, String profileImageUrl, String bannerImageUrl, List<Subreddit> subreddits);

    @Query("UPDATE account_table SET is_current_user = 1 WHERE username = :username")
    void markAccountCurrent(String username);

}
