package lilee.hd.anotterredditapp.database;

import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.model.account.Account;
import retrofit2.Callback;

public interface RepositoryInterface {
    void getAccessToken(String accessToken, Callback<TokenResponse> callBack);

    void refreshToken(String refreshToken, Callback<TokenResponse> callBack);

    void getUserInfo(String userName, Callback<Account> callBack);

    void getUserSubreddits(String[] userSubreddits, Callback<Account> callBack);


}
