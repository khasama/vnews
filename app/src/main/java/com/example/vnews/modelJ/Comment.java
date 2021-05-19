package com.example.vnews.modelJ;

public class Comment {
    public String idCmt;
    public String userName;
    public String content;
    public String date;
    public String avatar;

    public Comment(String idCmt, String userName, String content, String date, String avatar) {
        this.idCmt = idCmt;
        this.userName = userName;
        this.content = content;
        this.date = date;
        this.avatar = avatar;
    }

    public String getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(String idCmt) {
        this.idCmt = idCmt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
