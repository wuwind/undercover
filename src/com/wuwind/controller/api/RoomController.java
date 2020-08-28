package com.wuwind.controller.api;

import com.wuwind.dao.bean.Room;
import com.wuwind.dao.bean.User;
import com.wuwind.service.RoomService;
import com.wuwind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class RoomController {

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(RoomController.class);

    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    @RequestMapping("addRoom")
    @ResponseBody
    public Room addRoom(String name) {
        Room room = new Room();
        room.setName(name);
        room.setOpen(1);
        Object add = roomService.add(room);
        return room;
    }

    @RequestMapping("getAllRooms")
    @ResponseBody
    public List<Room> getAllRooms(HttpServletRequest request) {
        System.out.println("getAllRooms");
        List<Room> all = roomService.getAll();
        return all;
    }

    @RequestMapping("getAllUseRooms")
    @ResponseBody
    public List<Room> getAllUseRooms(HttpServletRequest request) {
        System.out.println("getAllRooms");
        List<Room> all = roomService.getAll();
        return all;
    }

    @RequestMapping("getAllOpenRooms")
    @ResponseBody
    public List<Room> getAllOpenRooms(HttpServletRequest request) {
        System.out.println("getAllRooms");
        List<Room> all = roomService.getAllOpened();
        return all;
    }

    @RequestMapping("closeRoom")
    @ResponseBody
    public Room closeRoom(Room room) {
        System.out.println("getAllRooms");
        room = roomService.select(room.getId());
        room.setOpen(0);
        roomService.update(room);
        return room;
    }

    @RequestMapping("updateRoom")
    @ResponseBody
    public Room updateRoom(Room room) {
        roomService.update(room);
        return room;
    }

    @RequestMapping("deleteRoom")
    @ResponseBody
    public Room deleteRoom(Room room) {
        roomService.delete(room);
        return room;
    }

    @RequestMapping("getRoomByUserId")
    @ResponseBody
    public Room getRoomByUserId(int[] userIds) {
        if (null == userIds)
            return null;
        User user = userService.getUserById(userIds[0]);
        if (null == user)
            return null;
        Room room = roomService.select(user.getRoomId());
        return room;
    }

}
