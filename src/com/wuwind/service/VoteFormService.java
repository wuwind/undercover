package com.wuwind.service;

import com.wuwind.dao.bean.VoteForm;

import java.util.List;

public interface VoteFormService {

    Object add(VoteForm voteForm);

    int delete(VoteForm voteForm);

    int update(VoteForm voteForm);

    VoteForm select(Object voteFormId);

    List<VoteForm> getAll();
    List<VoteForm> getVisibleAll();

    boolean isExist(VoteForm voteForm);

    List<VoteForm> getByUseId(Object userId);

    List<VoteForm> getByVoteId(Object voteId);
}
