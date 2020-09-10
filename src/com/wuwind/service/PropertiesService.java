package com.wuwind.service;

import com.wuwind.dao.bean.Properties;

import java.util.List;

public interface PropertiesService {

    Object add(Properties properties);

    int delete(Properties properties);

    int update(Properties properties);

    Properties select(Object propertiesId);

    List<Properties> getAll();
}
