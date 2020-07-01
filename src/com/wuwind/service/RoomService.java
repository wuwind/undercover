package com.wuwind.service;

import com.wuwind.dao.bean.Room;

import java.util.List;

public interface RoomService {

    Object add(Room room);

    int delete(Room room);

    int update(Room room);

    Room select(Object roomId);

    List<Room> getAll();

    List<Room> getAllByUserId(Object userId);


}
