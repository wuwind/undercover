package com.wuwind.dao.impl;

import com.wuwind.dao.VoteDao;
import com.wuwind.dao.bean.Vote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteDaoImpl extends BaseDaoImpl<Vote> implements VoteDao {
    @Override
    public List<Vote> getVisibleAll() {
        String sql = "select * from " + tbNmae + " where visible = 0 and (del is NULL or del<>1)";
        return queryList(sql);
    }
}
