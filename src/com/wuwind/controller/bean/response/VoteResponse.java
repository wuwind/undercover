package com.wuwind.controller.bean.response;

import com.wuwind.dao.bean.Vote;

import java.util.HashMap;
import java.util.Map;

public class VoteResponse {

    private Long id;
    private String title;

    private Map<String, String> map = new HashMap<>();

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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public VoteResponse(Vote vote) {
        id = vote.getId();
        title = vote.getTitle();
        String[] items = vote.getItems().split(",");
        String[] counts = vote.getCounts().split(",");
        for (int i = 0; i < counts.length; i++) {
            map.put(items[i], counts[i]);
        }
    }

}
