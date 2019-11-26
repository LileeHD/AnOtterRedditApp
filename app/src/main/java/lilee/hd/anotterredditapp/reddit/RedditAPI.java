package lilee.hd.anotterredditapp.reddit;

import lilee.hd.anotterredditapp.reddit.model.Feed;
import lilee.hd.anotterredditapp.reddit.model.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RedditAPI {

    @Headers("Content-Type: application/json")

// home display not logged
    @GET("subreddits/default.json?&raw_json=1&type=sr")
    Call<Feed> getData();

    // search
    @GET("r/subreddits/search.json?&raw_json=1&type=sr")
    Call<Feed> searchSubreddit(@Query("q") String subredditName,
                               @Query("t") String time, @Query("sort") String sort);
    @GET("r/{subreddit}/search.json?&raw_json=1&type=sr")
    Call<Feed> search(@Query("q") String subredditName,
                               @Query("t") String time, @Query("sort") String sort);


    @FormUrlEncoded
    @POST("api/v1/access_token")
    Call<TokenResponse> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirect_uri
    );
}
