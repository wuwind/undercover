package com.wuwind.service;

import com.wuwind.dao.bean.Vote;

import java.util.List;

public interface VoteService {

    Object add(Vote vote);

    int delete(Vote vote);

    int update(Vote vote);

    Vote select(Object voteId);

    List<Vote> selectByUserId(Object userId);

    List<Vote> getAll();

    List<Vote> getVisibleAll();

    boolean isExist(Vote vote);
}
