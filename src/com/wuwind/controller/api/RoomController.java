package com.wuwind.controller.api;

import com.wuwind.dao.bean.Room;
import com.wuwind.service.RoomService;
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

    @RequestMapping("addRoom")
    @ResponseBody
    public Room addRoom(HttpServletRequest request, Room room) {
        System.out.println("room "+room.getName());
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

    @RequestMapping("closeRoom")
    @ResponseBody
    public Room closeRoom(Room room) {
        System.out.println("getAllRooms");
        room = roomService.select(room.getId());
        room.setOpen(0);
        roomService.update(room);
        return room;
    }
}
