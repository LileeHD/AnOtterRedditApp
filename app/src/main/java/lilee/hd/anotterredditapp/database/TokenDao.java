package lilee.hd.anotterredditapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import lilee.hd.anotterredditapp.model.token.TokenResponse;

@Dao
public interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TokenResponse tokenResponse);

    @Update
    void update(TokenResponse tokenResponse);

    @Delete
    void delete(TokenResponse tokenResponse);

    @Query("SELECT * FROM token_table")
    TokenResponse getcurrentToken();

    @Query("SELECT * FROM token_table")
    TokenResponse getrefreshToken();
}
