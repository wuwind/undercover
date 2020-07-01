package com.wuwind.dao.impl;

import com.wuwind.dao.RoomDao;
import com.wuwind.dao.bean.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao {
    @Override
    public List<Room> getAllByUserId(Object userId) {
        String sql = "select * from " + tbNmae + " where userId = " + userId;
        return queryList(sql);
    }
}
