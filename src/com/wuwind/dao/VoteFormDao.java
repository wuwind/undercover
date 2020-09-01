package com.wuwind.dao;

import com.wuwind.dao.bean.VoteForm;

import java.util.List;

public interface VoteFormDao extends BaseDao<VoteForm> {

    List<VoteForm> getVisibleAll();

    List<VoteForm> getByUseId(Object userId);

    List<VoteForm> getByVoteId(Object voteId);
}
