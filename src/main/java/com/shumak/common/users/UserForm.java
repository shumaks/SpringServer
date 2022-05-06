package com.shumak.common.users;

public class UserForm {
    private String login;
    private String password;

    private String accessRights;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessRights() { return accessRights; }

    public void setAccessRights(String accessRights) { this.accessRights = accessRights; }
}
