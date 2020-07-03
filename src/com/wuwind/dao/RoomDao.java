package com.wuwind.dao;

import com.wuwind.dao.bean.Room;

import java.util.List;

public interface RoomDao extends BaseDao<Room> {

    List<Room> getAllByUserId(Object userId);

    List<Room> getAllOpened();

    List<Room> getAllUseRooms();
}
