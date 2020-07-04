package com.wuwind.dao.impl;

import com.wuwind.dao.AdviseDao;
import com.wuwind.dao.bean.Advise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdviseDaoImpl extends BaseDaoImpl<Advise> implements AdviseDao {
    @Override
    public List<Advise> getVisibleAll() {
        String sql = "select * from " + tbNmae + " where visible = 0 and (del is NULL or del<>1)";
        return queryList(sql);
    }
}
