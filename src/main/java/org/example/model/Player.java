package org.example.model;

public class Player {
    private int id;
    private String nickname;
    private String realName;
    private String role;
    private String country;

    public Player() {}

    public Player(int id, String nickname, String realName, String role, String country) {
        this.id = id;
        this.nickname = nickname;
        this.realName = realName;
        this.role = role;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
