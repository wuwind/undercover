package com.wuwind.dao;

import com.wuwind.dao.bean.Activity;

import java.util.List;

public interface ActivityDao extends BaseDao<Activity> {

    List<Activity> getVisibleAll();
}
