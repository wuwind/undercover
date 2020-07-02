package com.wuwind.test;

import com.wuwind.controller.api.GameController;
import com.wuwind.dao.bean.Game;
import com.wuwind.dao.bean.Room;
import com.wuwind.dao.bean.Word;
import com.wuwind.response.Response;
import com.wuwind.service.GameService;
import com.wuwind.service.RoomService;
import com.wuwind.service.WordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JunitTest extends BaseJunitTest {

    @Autowired
    WordService wordService;
    @Autowired
    GameService gameService;

    @Autowired
    RoomService roomService;


    @Autowired
    GameController gameController;



    @Test
    public void getGameByUser() {
        Response gameByUser = gameController.getGameByUser(new int[]{62});
        System.out.println(gameByUser.toString());
    }

    @Test
    public void addRoom() {
        Room room = new Room();
        room.setName("rrr");
        roomService.add(room);
    }

    @Test
    public void delUserById() {
//        int i = userService.delUserById("admin");
//        System.out.println("删除i：" + i);
    }


    @Test
    public void getUserById() {
//        User admin = userDao.getUserById("41");
//        System.out.println(admin);
    }

    @Test
    public void addGame() {
        Game game = new Game();
        game.setBlank(1);
        game.setAudience(1);
        game.setSequence("1,2,3,4,5,6");
        gameService.addGame(game);
    }

    @Test
    public void addWord() {
        Word word = new Word();
//        word.setId(11l);
        word.setW1("hello");
        word.setW2("nihao");
        Object i = wordService.add(word);
        System.out.println(i);
    }


    @Test
    public void getAllWords() {
        List<Word> allMenu = wordService.getAll();
        for (Word menu : allMenu) {
            System.out.println(menu.getW1());
        }
    }

    @Test
    public void delAllUser() {
//        userService.deleteAll();
//        userService.delUserById(42);

//        d.add(46);
//        userService.delUserById(d);
//        User u = new User();
//        u.setId(37);
//        List<User> d = new ArrayList<>();
//        d.add(u);
////        d.add(48);
//        userDao.delete(d);
    }

    @Test
    public void getGameByRoomId() {
        List<Game> allByRoomId = gameService.getAllByRoomId(1);
    }
    @Test
    public void updateUser() {
//        List<User> d = new ArrayList<>();
//        User user = new User();
//        user.setId(39);
//        user.setName("admin2");
//        user.setPassword("123456");
//        d.add(user);
//        int i = userDao.update(d);
//        System.out.println("修改：" + i);
    }

}