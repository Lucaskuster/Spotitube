package Spotitube.controllers.dto;

public class LoginRequestDTO {
    private String user = "Lucas";
    private String password = "Lucas";

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
