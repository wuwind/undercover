package com.wuwind.dao;

import com.wuwind.dao.bean.ActivityForm;

import java.util.List;

public interface ActivityFormDao extends BaseDao<ActivityForm> {

    List<ActivityForm> getVisibleAll();

    boolean isExist(ActivityForm activityForm);
}
