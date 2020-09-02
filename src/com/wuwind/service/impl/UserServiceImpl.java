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
    public User getUserById(Object id) {
        return userDao.queryById(id);
    }

    @Override
    public List<User> getUserByName(String name) {
        return userDao.queryByName(name);
    }

    @Override
    public List<User> getAllByRoomId(Integer roomId) {
        return userDao.getAllByRoomId(roomId);
    }

    @Override
    public int delUserById(Object userId) {
        return userDao.deleteById(userId);
    }

    @Override
    public Object addWxUser(User user) {
        Object add;
        List<User> users = userDao.queryByWxId(user.getWxId());
        if(null != users && users.size()>0) {
            User user1 = users.get(0);
            user1.setWxName( user.getWxName());
            user1.setWxPhoto(user.getWxPhoto());
            userDao.update(user1);
            add = user1.getId();
        } else {
            add = userDao.add(user);
        }
        return add;
    }

}
