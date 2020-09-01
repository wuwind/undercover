package com.wuwind.service.impl;

import com.wuwind.dao.VoteFormDao;
import com.wuwind.dao.bean.VoteForm;
import com.wuwind.service.VoteFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoteFormServiceImpl implements VoteFormService {

    @Resource
    VoteFormDao voteFormDao;

    @Override
    public Object add(VoteForm voteForm) {
        return voteFormDao.add(voteForm);
    }

    @Override
    public int delete(VoteForm voteForm) {
        return voteFormDao.delete(voteForm);
    }

    @Override
    public int update(VoteForm voteForm) {
        return voteFormDao.update(voteForm);
    }

    @Override
    public VoteForm select(Object voteFormId) {
        return voteFormDao.queryById(voteFormId);
    }

    @Override
    public List<VoteForm> getAll() {
        return voteFormDao.getAll();
    }

    @Override
    public List<VoteForm> getVisibleAll() {
        return  voteFormDao.getVisibleAll();
    }

    @Override
    public boolean isExist(VoteForm voteForm) {
        return false;
    }

    @Override
    public List<VoteForm> getByUseId(Object userId) {
        return voteFormDao.getByUseId(userId);
    }

    @Override
    public  List<VoteForm> getByVoteId(Object voteId) {
        return voteFormDao.getByVoteId(voteId);
    }

}
