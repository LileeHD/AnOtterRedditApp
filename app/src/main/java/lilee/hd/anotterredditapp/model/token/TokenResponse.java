package lilee.hd.anotterredditapp.model.token;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lilee.hd.anotterredditapp.util.ConverterUtil;

@Entity(tableName = "token_table")
public class TokenResponse {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("token_type")
    @Expose
    private String tokenType;


    @SerializedName("expires_in")
    @Expose
    private long expiresIn;

    @SerializedName("scope")
    @Expose
    private String scope;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
//--------------------------------Constructors ------------------------------
//    @Ignore
    public TokenResponse(String accessToken, String tokenType, long expiresIn, String scope, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.scope = scope;
        this.refreshToken = refreshToken;
    }

    public TokenResponse(TokenInterface tokenInterface) {
        this.id = tokenInterface.getId();
        this.accessToken = tokenInterface.getAccessToken();
        this.tokenType = tokenInterface.getTokenType();
        this.expiresIn = tokenInterface.getExpiresIn();
        this.scope = tokenInterface.getScope();
        this.refreshToken = tokenInterface.getRefreshToken();
    }
//--------------------------------Getters Setters--------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

// ----------------------------toString---------------------
    @Override
    public String toString() {
        return "TokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }


}
