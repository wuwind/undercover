package com.wuwind.dao.impl;

import com.wuwind.dao.UserDao;
import com.wuwind.dao.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public List<User> getAllByRoomId(Integer roomId) {
        String sql = "select * from " + tbNmae + " where roomId = " + roomId;
        return queryList(sql);
    }

    @Override
    public List<User> queryByName(String name) {
        String sql = "select * from " + tbNmae + " where users = '" + name + "'";
        return queryList(sql);
    }
}
