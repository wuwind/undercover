package com.wuwind.dao.bean;

public class Vote {
    private Long id;
    private String title;
    /**
     * 逗号隔开
     */
    private String items;
    /**
     * 逗号隔开
     */
    private String counts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", items='" + items + '\'' +
                ", counts='" + counts + '\'' +
                '}';
    }
}
