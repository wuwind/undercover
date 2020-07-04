package com.wuwind.dao.bean;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.


/**
 * Entity mapped to table "GAME".
 */
public class Game implements Cloneable {

    private Long id;
    private Long wordId;
    private Integer count;
    private Integer normal;
    private Integer undercover;
    private Integer blank;
    private Integer audience;
    private String sequence;
    private String outSequence;
    private String lookSequence;
    private Long roomId;
    private Integer finish;
    private Integer win;//1平民获胜 2卧底获胜

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", wordId=" + wordId +
                ", count=" + count +
                ", normal=" + normal +
                ", undercover=" + undercover +
                ", blank=" + blank +
                ", audience=" + audience +
                ", sequence='" + sequence + '\'' +
                ", outSequence='" + outSequence + '\'' +
                ", lookSequence='" + lookSequence + '\'' +
                ", roomId=" + roomId +
                ", finish=" + finish +
                ", win=" + win +
                '}';
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getUndercover() {
        return undercover;
    }

    public void setUndercover(Integer undercover) {
        this.undercover = undercover;
    }

    public Integer getBlank() {
        return blank;
    }

    public void setBlank(Integer blank) {
        this.blank = blank;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getOutSequence() {
        return outSequence;
    }

    public void setOutSequence(String outSequence) {
        this.outSequence = outSequence;
    }

    public String getLookSequence() {
        return lookSequence;
    }

    public void setLookSequence(String lookSequence) {
        this.lookSequence = lookSequence;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

}
