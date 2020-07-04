package com.wuwind.dao;

import com.wuwind.dao.bean.Advise;

import java.util.List;

public interface AdviseDao extends BaseDao<Advise> {

    List<Advise> getVisibleAll();
}
