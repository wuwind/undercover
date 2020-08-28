package com.wuwind.dao.impl;

import com.wuwind.dao.ActivityFormDao;
import com.wuwind.dao.bean.ActivityForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityFormDaoImpl extends BaseDaoImpl<ActivityForm> implements ActivityFormDao {
    @Override
    public List<ActivityForm> getVisibleAll() {
        String sql = "select * from " + tbNmae + " where visible = 0 and (del is NULL or del<>1)";
        return queryList(sql);
    }

    @Override
    public boolean isExist(ActivityForm activityForm) {
        String sql = "select * from " + tbNmae + " where userId = " + activityForm.getUserId()
                + " and activityId = " + activityForm.getActivityId()
                + " and (del is NULL or del<>1) limit 1";
        List<ActivityForm> activityForms = queryList(sql);
        return queryList(sql) != activityForms && activityForms.size() > 0;
    }
}
