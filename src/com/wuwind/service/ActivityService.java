package com.wuwind.service;

import com.wuwind.dao.bean.Activity;

import java.util.List;

public interface ActivityService {

    Object add(Activity activity);

    int delete(Activity activity);

    int update(Activity activity);

    Activity select(Object activityId);

    List<Activity> getAll();
    List<Activity> getVisibleAll();
}
