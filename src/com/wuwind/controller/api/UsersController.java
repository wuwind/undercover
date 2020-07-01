package com.wuwind.controller.api;

import com.wuwind.controller.bean.GameUser;
import com.wuwind.dao.bean.Game;
import com.wuwind.dao.bean.Room;
import com.wuwind.dao.bean.User;
import com.wuwind.dao.bean.Word;
import com.wuwind.response.Response;
import com.wuwind.service.GameService;
import com.wuwind.service.RoomService;
import com.wuwind.service.UserService;
import com.wuwind.service.WordService;
import com.wuwind.util.StrConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("api")
public class UsersController {

    private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    WordService wordService;
    @Autowired
    RoomService roomService;

    @RequestMapping("users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAll();
    }

    @RequestMapping("ready")
    @ResponseBody
    public int ready(String userId) {
        User user = userService.getUserById(Long.parseLong(userId));
        user.setReady(1);
        return userService.updateUser(user);
    }

    @RequestMapping("addUsers")
    @ResponseBody
    public synchronized Response addUsers(GameUser gUser) {
        System.out.println("addUsers " + gUser.toString());
        Response response = new Response();

        List<String> ids = new ArrayList<>();
        Room room = roomService.select(gUser.getRoomId());
        int roomNum = room.getNum();
        for (String user : gUser.getUsers()) {
            roomNum++;
            User u = new User();
            u.setUsers(user);
            u.setReady(0);
            u.setWxName(gUser.getWxName());
            u.setWxPhoto(gUser.getWxPhoto());
            u.setuOut(0);
            u.setRoomId((long) gUser.getRoomId());
            u.setCreateTime(simpleDate.format(new Date()));
            u.setGameNo(roomNum);
            ids.add(userService.addUser(u).toString());
        }
        room.setNum(roomNum);
        int update = roomService.update(room);
        if(update<1) {
            response.setCode(0);
            response.setMsg("加入房间失败，请重试");
            return response;
        }
//        gameService.getAllByRoomId(gUser.getRoomId());
        response.setData(ids);
        return response;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public synchronized Response addUser(HttpServletRequest request, GameUser gUser) {
        System.out.println("addUser " + this.toString());
        Response response = new Response();
        if (gUser.getWxId() == null) {
            response.setMsg("wx id is null");
            return response;
        }
        User user = new User();
        user.setWxId(gUser.getWxId());
        List<User> all = userService.getAll();
        user.setNum(gUser.getNum());
        user.setReady(0);
        boolean isUpdate = false;
        for (User user1 : all) {
            if (user.getWxId().equals(user1.getWxId())) {
                isUpdate = true;
                user.setId(user1.getId());
                if (!isGameFinish(user1)) {
                    response.setData(getWordMaps(user1));
                    return response;
                }
                break;
            }
        }
        List<Game> games = gameService.getAll();
        Game lastGame = games.get(games.size() - 1);
        int count = gUser.getNum();
        String lookSequence = lastGame.getLookSequence();
        if (null != lookSequence && lookSequence.length() + count > lastGame.getSequence().length()) {
            response.setMsg("卡片被抢光啦!");
            return response;
        }
        if (null == lookSequence) {
            lookSequence = "";
        }
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();
        int index = lookSequence.length();
        int[] indexs = new int[count];
        Word word = wordService.select(lastGame.getWordId());
        for (int i = 0; i < count; i++) {
            indexs[i] = index;
            lookSequence += "1";
            maps.put(gUser.getUsers().get(i), getWord(lastGame.getSequence(), index, word));
            index++;
        }
        lastGame.setLookSequence(lookSequence);
        gameService.updateGame(lastGame);
        StringBuilder wordIsBuild = new StringBuilder();
        StringBuilder wordsBuild = new StringBuilder();
        StringBuilder usersBuild = new StringBuilder();
        for (int i : indexs) {
            wordIsBuild.append(i).append(",");
        }
        wordIsBuild.deleteCharAt(wordIsBuild.length() - 1);
        for (String value : maps.values()) {
            wordsBuild.append(value).append(",");
        }
        wordsBuild.deleteCharAt(wordsBuild.length() - 1);
        for (String value : gUser.getUsers()) {
            usersBuild.append(value).append(",");
        }
        usersBuild.deleteCharAt(usersBuild.length() - 1);
        user.setWordIS(wordIsBuild.toString());
        user.setWords(wordsBuild.toString());
        user.setGameId(lastGame.getId());
        user.setWxPhoto(gUser.getWxPhoto());
        user.setUsers(usersBuild.toString());
        if (!isUpdate) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        response.setData(maps);
        return response;
    }

    private Map<String, String> getWordMaps(User user) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();
        String[] users = user.getUsers().split(",");
        String[] words = user.getWords().split(",");
        for (int i = 0; i < user.getNum(); i++) {
            if (isReady(user, i))
                continue;
            maps.put(users[i], words[i]);
        }
        return maps;
    }

    private boolean isReady(User user, int index) {
        Integer ready = user.getReady();
        return (ready >> index & 1) > 0;
    }

    private boolean isGameFinish(User user) {
        Game game = gameService.getGameById(user.getGameId());
        return game.getFinish() != null && game.getFinish() == 1;
    }

    private String getWord(String sequenceStr, int index, Word word) {
        byte[] sequence = StrConverter.toByteArray(sequenceStr);
        int type = sequence[index];
        switch (type) {
            case 0:
                return word.getW1();
            case 1:
                return word.getW2();
            case 2:
                return "白板";
            case 3:
                return "观众";
        }
        return null;
    }

}
