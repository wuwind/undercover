package com.wuwind.dao.impl;

import com.wuwind.dao.VoteFormDao;
import com.wuwind.dao.bean.VoteForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteFormDaoImpl extends BaseDaoImpl<VoteForm> implements VoteFormDao {
    @Override
    public List<VoteForm> getVisibleAll() {
        String sql = "select * from " + tbNmae + " where (del is NULL or del<>1)";
        return queryList(sql);
    }

    @Override
    public List<VoteForm> getByUseId(Object userId) {
        String sql = "select * from " + tbNmae + " where (del is NULL or del<>1) and userId="+userId;
        return queryList(sql);
    }

    @Override
    public List<VoteForm> getByVoteId(Object voteId) {
        String sql = "select * from " + tbNmae + " where (del is NULL or del<>1) and voteId="+voteId;
        return queryList(sql);
    }
}
