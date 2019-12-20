package lilee.hd.anotterredditapp.reddit;

import android.util.Base64;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lilee.hd.anotterredditapp.model.token.TokenResponse;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Constants {
    /**
     * Tags
     */
    public static final String TAG_TOKEN = "OTTER";
    /**
     * Postman is my best friend
     * https://www.getpostman.com/downloads/
     */

    public static final String CLIENT_NAME = "An Otter Reddit App";
    /**
     * Base urls
     */
    public static final String BASE_URL = "https://www.reddit.com/";
    public static final String DEFAULT_URL = "https://www.reddit.com/subreddits/default.json";

    /**
     * Authorization urls
     * "https://www.reddit.com/api/v1/authorize.compact?client_id=CLIENT_ID&response_type=TYPE&state=STATE&redirect_uri=REDIRECT_URI&duration=DURATION&scope=SCOPE_STRING";
     */
    public static final String OAUTH_URL_ACCESS = "https://oauth.reddit.com/";
    public static final String OAUTH_BASE_URL = "https://www.reddit.com/api/v1/authorize.compact";


    /**
     * Token urls
     */
//    public static final String ACCESS_TOKEN_URL  = "https://www.reddit.com/api/v1/access_token/";
    //    public static final String ACCESS_TOKEN_URL = "https://oauth.reddit.com/api/v1/access_token/";
    /**
     * Access Token keys
     */
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";
    public static final String TOKEN_TYPE_KEY = "token_type";
    public static final String TOKEN_TYPE = "bearer";
    public static final String EXPIRE_IN_KEY = "expires_in";
    /**
     * Reddit request keys
     */
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_ID = "x7u8VJCpOneunQ";
    public static final String PASSWORD = "";

    public static final String RESPONSE_TYPE_KEY = "response_type";
    public static final String RESPONSE_TYPE = "code";

    public static final String STATE_KEY = "state";
//    public static final String STATE = UUID.randomUUID().toString().replace("-", "");
    public static final String STATE = "ZENYATTA";

    public static final String ERROR_KEY = "error";
    public static final String ERROR = "";

    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String REDIRECT_URI = "lilee.hd://callback";

    public static final String DURATION_KEY = "duration";
    public static final String DURATION = "permanent";

    public static final String SCOPE_KEY = "scope";
    public static final String SCOPE = "identity, mysubreddits, read, save, subscribe, vote";

    public static final String GRANT_TYPE_KEY = "grant_type";

    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    public static String AUTHORIZATION_KEY = "Authorization";
    public static String AUTHORIZATION_CODE_KEY = "authorization_code";
    public static final String USER_AGENT_KEY = "User-Agent";
    public static final String USER_AGENT = "";

    public static final String AUTHORIZATION_BEARER = "bearer";

    public static String SORT_KEY = "sort";
    public static String SORT_NEW = "new";
    public static String SORT_POPULAR = "popular";
    public static String SORT_DEFAULT = "default";

    public static Map<String, String> getHttpBasicAuthHeader() {
        Map<String, String> params = new HashMap<>();
        String credentials = Credentials.basic(CLIENT_ID, "");
        params.put(AUTHORIZATION_KEY, credentials);
        return params;
    }

    public static Map<String, String> getOAuthHeader(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put(AUTHORIZATION_KEY, AUTHORIZATION_BEARER + accessToken);
        params.put(USER_AGENT_KEY, USER_AGENT);
        return params;
    }

    public static RequestBody getRequestBody(String s) {
        return RequestBody.create(s, MediaType.parse("text/plain"));
    }

}
