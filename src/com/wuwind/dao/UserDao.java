package com.wuwind.dao;


import com.wuwind.dao.bean.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {


    List<User> getAllByRoomId(Integer roomId);

    List<User> queryByName(String name);

    List<User> queryByWxId(String wxId);
}
