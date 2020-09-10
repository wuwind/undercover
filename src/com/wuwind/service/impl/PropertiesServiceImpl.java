package com.wuwind.service.impl;

import com.wuwind.dao.PropertiesDao;
import com.wuwind.dao.bean.Properties;
import com.wuwind.service.PropertiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Resource
    PropertiesDao propertiesDao;

    @Override
    public Object add(Properties properties) {
        return propertiesDao.add(properties);
    }

    @Override
    public int delete(Properties properties) {
        return propertiesDao.delete(properties);
    }

    @Override
    public int update(Properties properties) {
        return propertiesDao.update(properties);
    }

    @Override
    public Properties select(Object propertiesId) {
        return propertiesDao.queryById(propertiesId);
    }

    @Override
    public List<Properties> getAll() {
        return propertiesDao.getAll();
    }
}
