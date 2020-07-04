package com.wuwind.service.impl;

import com.wuwind.dao.AdviseDao;
import com.wuwind.dao.bean.Advise;
import com.wuwind.service.AdviseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdviseServiceImpl implements AdviseService {

    @Resource
    AdviseDao adviseDao;

    @Override
    public Object add(Advise advise) {
        return adviseDao.add(advise);
    }

    @Override
    public int delete(Advise advise) {
        return adviseDao.delete(advise);
    }

    @Override
    public int update(Advise advise) {
        return adviseDao.update(advise);
    }

    @Override
    public Advise select(Object adviseId) {
        return adviseDao.queryById(adviseId);
    }

    @Override
    public List<Advise> getAll() {
        return adviseDao.getAll();
    }

    @Override
    public List<Advise> getVisibleAll() {
        return  adviseDao.getVisibleAll();
    }
}
