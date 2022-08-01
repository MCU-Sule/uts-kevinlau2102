package com.uts.uts_2072030.Model;

public class User {
    private int idUser;
    private String UserName;
    private String UserPassword;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public User(int idUser, String userName, String userPassword) {
        this.idUser = idUser;
        UserName = userName;
        UserPassword = userPassword;
    }

    @Override
    public String toString() {
        return UserName;
    }
}
