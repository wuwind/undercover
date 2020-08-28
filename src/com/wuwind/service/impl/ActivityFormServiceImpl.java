package com.wuwind.service.impl;

import com.wuwind.dao.ActivityFormDao;
import com.wuwind.dao.bean.ActivityForm;
import com.wuwind.service.ActivityFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityFormServiceImpl implements ActivityFormService {

    @Resource
    ActivityFormDao activityFormDao;

    @Override
    public Object add(ActivityForm activityForm) {
        return activityFormDao.add(activityForm);
    }

    @Override
    public int delete(ActivityForm activityForm) {
        return activityFormDao.delete(activityForm);
    }

    @Override
    public int update(ActivityForm activityForm) {
        return activityFormDao.update(activityForm);
    }

    @Override
    public ActivityForm select(Object activityFormId) {
        return activityFormDao.queryById(activityFormId);
    }

    @Override
    public List<ActivityForm> getAll() {
        return activityFormDao.getAll();
    }

    @Override
    public List<ActivityForm> getVisibleAll() {
        return  activityFormDao.getVisibleAll();
    }

    @Override
    public boolean isExist(ActivityForm activityForm) {
        return activityFormDao.isExist(activityForm);
    }
}
