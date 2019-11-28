package lilee.hd.anotterredditapp.reddit;

import java.util.UUID;

public class Constants {
    /**
     * Tags
     */
    public static final String TAG_TOKEN = "OTTER";
    public static final String TAG = "Take a bow";
    /**
     * Postman is out best friend
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
    public static final String ACCESS_TOKEN_URL  = "https://www.reddit.com/api/v1/access_token/";
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
     * Everything else
     */
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_ID = "x7u8VJCpOneunQ";
    public static final String PASSWORD = "";

    public static final String RESPONSE_TYPE_KEY = "response_type";
    public static final String RESPONSE_TYPE = "code";

    public static final String STATE_KEY = "state";
//    public static final String STATE = "NIER_OTTER_MATA";
        public static final String STATE = UUID.randomUUID().toString().replace("-", "");

    public static final String ERROR_KEY = "error";
    public static final String ERROR = "";

    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String REDIRECT_URI = "lilee.hd://callback";

    public static final String DURATION_KEY = "duration";
    public static final String DURATION = "permanent";

    public static final String SCOPE_KEY = "scope";
    public static final String SCOPE = "identity, mysubreddits, read, report, save, submit, subscribe, vote";
//    public static final String SCOPE = "identity, edit, flair, history, modconfig, modflair, modlog, modposts, modwiki, mysubreddits, privatemessages, read, report, save, submit, subscribe, vote, wikiedit, wikiread";

    public static final String GRANT_TYPE_KEY = "grant_type";
    public static final String GRANT_TYPE = "authorization_code";

    public static String AUTHORIZATION_KEY = "Authorization";

    public static String SORT_KEY = "sort";
    public static String SORT_NEW = "new";
    public static String SORT_POPULAR = "popular";
    public static String SORT_DEFAULT = "default";
    public static String SORT_GOLD = "gold";


    //
    public static String Authcode;
    public static String Tokentype;
    public static String Expiresin;
    public static String Scope;
    public static String State;
    public static String Refreshtoken;
}
