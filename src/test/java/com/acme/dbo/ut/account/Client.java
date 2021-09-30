package com.acme.dbo.ut.account;

public class Client {
    private String login;
    private String salt;
    private String secret;
    private int id;
    private String created;
    private boolean enabled;

    public Client() {
    }

    public Client(String login, String salt, String secret) {
        this.login = login;
        this.salt = salt;
        this.secret = secret;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", salt='" + salt + '\'' +
                ", secret='" + secret + '\'' +
                ", id=" + id +
                ", created='" + created + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
