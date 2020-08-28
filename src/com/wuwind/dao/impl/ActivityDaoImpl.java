package com.wuwind.dao.impl;

import com.wuwind.dao.ActivityDao;
import com.wuwind.dao.bean.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao {
    @Override
    public List<Activity> getVisibleAll() {
        String sql = "select * from " + tbNmae + " where visible = 0 and (del is NULL or del<>1)";
        return queryList(sql);
    }
}
