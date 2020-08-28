package com.wuwind.dao;

import com.wuwind.dao.bean.Vote;

import java.util.List;

public interface VoteDao extends BaseDao<Vote> {

    List<Vote> getVisibleAll();
}
