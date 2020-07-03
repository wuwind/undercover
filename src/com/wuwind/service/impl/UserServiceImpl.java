package com.wuwind.service.impl;

import com.wuwind.dao.bean.User;
import com.wuwind.dao.UserDao;
import com.wuwind.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Object addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.queryById(id);
    }

    @Override
    public List<User> getAllByRoomId(Integer roomId) {
        return userDao.getAllByRoomId(roomId);
    }

}
