package com.wuwind.dao.bean;

public class User {
    private Long id;
    private Long gameId;
    private String wxId;
    private String wxName;
    private String wxPhoto;
    private String users;
    private String words;
    private String wordIS;
    private Integer ready;
    private Integer uOut;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getWxPhoto() {
        return wxPhoto;
    }

    public void setWxPhoto(String wxPhoto) {
        this.wxPhoto = wxPhoto;
    }

    public Integer getReady() {
        return ready;
    }

    public void setReady(Integer ready) {
        this.ready = ready;
    }

    public Integer getuOut() {
        return uOut;
    }

    public void setuOut(Integer uOut) {
        this.uOut = uOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getWordIS() {
        return wordIS;
    }

    public void setWordIS(String wordIS) {
        this.wordIS = wordIS;
    }
}
