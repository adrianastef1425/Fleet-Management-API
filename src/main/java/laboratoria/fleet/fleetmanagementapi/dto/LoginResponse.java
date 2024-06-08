package laboratoria.fleet.fleetmanagementapi.dto;

public class LoginResponse {

    private String accessToken;

    private long expiresIn;

    private LoginUserResponse user;

    public LoginUserResponse getUser() {
        return user;
    }

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, long expiresIn, LoginUserResponse user) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }

    public void setUser(LoginUserResponse user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}