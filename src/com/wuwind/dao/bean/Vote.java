package com.wuwind.dao.bean;

public class Vote {
    private Long id;
    private Integer userId;
    private String title;
    /**
     * 逗号隔开
     */
    private String items;
    /**
     * 逗号隔开
     */
    private String counts;
    /**
     * 0 单选
     * 1 多选
     */
    private Integer type;
    /**
     * 参数类型
     */
    private Integer properties;

    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getProperties() {
        return properties;
    }

    public void setProperties(Integer properties) {
        this.properties = properties;
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
