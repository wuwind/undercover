package com.wuwind.service.impl;

import com.wuwind.dao.VoteDao;
import com.wuwind.dao.bean.Vote;
import com.wuwind.service.VoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Resource
    VoteDao voteDao;

    @Override
    public Object add(Vote vote) {
        return voteDao.add(vote);
    }

    @Override
    public int delete(Vote vote) {
        return voteDao.delete(vote);
    }

    @Override
    public int update(Vote vote) {
        return voteDao.update(vote);
    }

    @Override
    public Vote select(Object voteId) {
        return voteDao.queryById(voteId);
    }

    @Override
    public List<Vote> getAll() {
        return voteDao.getAll();
    }

    @Override
    public List<Vote> getVisibleAll() {
        return  voteDao.getVisibleAll();
    }

    @Override
    public boolean isExist(Vote vote) {
        return false;
    }

}
