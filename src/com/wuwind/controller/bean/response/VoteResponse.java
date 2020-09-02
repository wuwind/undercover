package com.wuwind.controller.bean.response;

import com.alibaba.fastjson.JSON;
import com.wuwind.controller.bean.VoteBean;
import com.wuwind.dao.bean.Vote;

import java.util.List;

public class VoteResponse {

    private Long id;
    private String title;

    private List<VoteBean> votes;

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

    public List<VoteBean> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteBean> votes) {
        this.votes = votes;
    }

    public VoteResponse(Vote vote) {
        id = vote.getId();
        title = vote.getTitle();
        votes = JSON.parseArray(vote.getItems(), VoteBean.class);
    }

    public VoteResponse(){}

}
