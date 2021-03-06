package lilee.hd.anotterredditapp.reddit;

import java.util.Map;

import lilee.hd.anotterredditapp.model.subreddit.Subreddit;
import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditAPI {

    /**
     * Home data
     * @return
     */
    @Headers("Content-Type: application/json")

    // home display not logged in
    @GET(".json?&raw_json=1&type=link")
    Call<Feed> getHomeFeed();

    @GET("search.json?q=otter&t=new&raw_json=1&type=link")
    Call<Feed> getOtterFeed();

//    Comments
//    @GET("r/{subreddit}.json")
//    Call<Feed> getPosts(@Path("subreddit") String subreddit);

//    @GET("{subreddit}/comments/{id}.json")
//    Call<List<PostDetail>> getComments(@Path("subreddit") String subreddit, @Path("id") String id);

    // home display search
    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> searchPost(@Query("q") String postName,
                          @Query("t") String time);

    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> inputResult(@Query("q") String postName);

    @GET("{subreddit}/about.json")
    Call<Subreddit> getSubreddit(@Path("subreddit") String subreddit);

    @GET("r/{subreddit_list_name}/new.json")
    Call<Feed>getSavedFeed(@Path("subreddit_list_name") String subredditList);

    /**
     * access token and user data
//     * @param grantType
//     * @param code
//     * @param redirect_uri
     * @return
     */
    @FormUrlEncoded
    @POST("api/v1/access_token")
    Call<TokenResponse> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirect_uri
    );
    @FormUrlEncoded
    @POST("api/v1/access_token")
    Call<String> getAccessTokenAuth(
            @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params
    );

    @FormUrlEncoded
    @POST("api/v1/access_token")
    Call<TokenResponse> getRefreshToken(
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken
    );

    /**
     * From here use: https://oauth.reddit.com
     * @return
     */
    @GET("api/v1/me?raw_json=1")
    Call<Account> getMyInfo();

    @GET("subreddits/mine/subscriber?raw_json=1")
    Call<Subreddit> getSubscribedThing();


}
