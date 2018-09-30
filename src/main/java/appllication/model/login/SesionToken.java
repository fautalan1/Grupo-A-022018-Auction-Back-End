package appllication.model.login;

import appllication.entity.User;

public class SesionToken {

    private User user;

    private String token;

    public SesionToken(User user, String token) {
        super();
        setUser(user);
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
