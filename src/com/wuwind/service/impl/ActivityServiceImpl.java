package com.wuwind.service.impl;

import com.wuwind.dao.ActivityDao;
import com.wuwind.dao.bean.Activity;
import com.wuwind.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityDao activityDao;

    @Override
    public Object add(Activity activity) {
        return activityDao.add(activity);
    }

    @Override
    public int delete(Activity activity) {
        return activityDao.delete(activity);
    }

    @Override
    public int update(Activity activity) {
        return activityDao.update(activity);
    }

    @Override
    public Activity select(Object activityId) {
        return activityDao.queryById(activityId);
    }

    @Override
    public List<Activity> getAll() {
        return activityDao.getAll();
    }

    @Override
    public List<Activity> getVisibleAll() {
        return  activityDao.getVisibleAll();
    }
}
