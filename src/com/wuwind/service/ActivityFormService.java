package com.wuwind.service;

import com.wuwind.dao.bean.ActivityForm;

import java.util.List;

public interface ActivityFormService {

    Object add(ActivityForm activityForm);

    int delete(ActivityForm activityForm);

    int update(ActivityForm activityForm);

    ActivityForm select(Object activityFormId);

    List<ActivityForm> getAll();
    List<ActivityForm> getVisibleAll();

    boolean isExist(ActivityForm activityForm);
}
