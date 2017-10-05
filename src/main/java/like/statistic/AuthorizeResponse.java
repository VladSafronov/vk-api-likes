package like.statistic;

public class AuthorizeResponse {
    String access_token;
    String expires_in;

    @Override
    public String toString() {
        return "AuthorizeResponse{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
