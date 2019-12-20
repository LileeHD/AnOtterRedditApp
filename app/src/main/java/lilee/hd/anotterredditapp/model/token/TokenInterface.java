package lilee.hd.anotterredditapp.model.token;

public interface TokenInterface {
    int getId();

    String getAccessToken();

    String getTokenType();

    Integer getExpiresIn();

    String getScope();

    String getRefreshToken();

}
