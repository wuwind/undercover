package com.wuwind.service;

import com.wuwind.dao.bean.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    Object addUser(User user);

    int updateUser(User user);

    User getUserById(long id);

}
