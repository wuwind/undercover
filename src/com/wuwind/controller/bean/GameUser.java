package com.wuwind.controller.bean;

import java.util.List;

public class GameUser {

    private int num;
    private String wxId;
    private String wxCode;
    private String wxName;
    private String wxPhoto;
    private List<String> users;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getWxPhoto() {
        return wxPhoto;
    }

    public void setWxPhoto(String wxPhoto) {
        this.wxPhoto = wxPhoto;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
