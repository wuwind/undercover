package com.wuwind.service.impl;

import com.wuwind.dao.RoomDao;
import com.wuwind.dao.bean.Room;
import com.wuwind.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    RoomDao roomDao;

    @Override
    public Object add(Room room) {
        return roomDao.add(room);
    }

    @Override
    public int delete(Room room) {
        return roomDao.delete(room);
    }

    @Override
    public int update(Room room) {
        return roomDao.update(room);
    }

    @Override
    public Room select(Object roomId) {
        return roomDao.queryById(roomId);
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getAllOpened() {
        return roomDao.getAllOpened();
    }

    @Override
    public List<Room> getAllUseRooms() {
        return roomDao.getAllUseRooms();
    }

    @Override
    public List<Room> getAllByUserId(Object userId) {
        return roomDao.getAllByUserId(userId);
    }
}
