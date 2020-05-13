package com.wuwind.controller.api;

import com.wuwind.bean.Game;
import com.wuwind.bean.User;
import com.wuwind.bean.Word;
import com.wuwind.response.Response;
import com.wuwind.service.GameService;
import com.wuwind.service.UserService;
import com.wuwind.service.WordService;
import com.wuwind.util.StrConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api")
public class UsersController {

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    WordService wordService;

    @RequestMapping("users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAll();
    }

    @RequestMapping("addUser")
    @ResponseBody
    public synchronized Response addUser(HttpServletRequest request, User user) {
        Response response = new Response();
        if (user.getWxId() == null) {
            response.setMsg("wx id is null");
            return response;
        }
        List<User> all = userService.getAll();
        boolean isUpdate = false;
        for (User user1 : all) {
            if (user.getWxId().equals(user1.getWxId())) {
                isUpdate = true;
                user.setId(user1.getId());
                break;
            }
        }
        List<Game> games = gameService.getAll();
        Game lastGame = games.get(games.size()-1);
        String users = user.getUsers();
        String[] gUsers = users.split(",");
        int count = gUsers.length;
        String lookSequence = lastGame.getLookSequence();
        if (null != lookSequence && lookSequence.length()+ count > lastGame.getSequence().length()) {
            response.setMsg("卡片被抢光啦!");
            return response;
        }
        if(null == lookSequence) {
            lookSequence = "";
        }
        Map<String, String> maps = new HashMap<>();
        int index = lookSequence.length();
        int[] indexs = new int[count];
        Word word = wordService.select(lastGame.getWordId());
        for (int i = 0; i < count; i++) {
            indexs[i] = index;
            lookSequence += "1";
            maps.put(gUsers[i], getWord(lastGame.getSequence(), index, word));
            index++;
        }
        lastGame.setLookSequence(lookSequence);
        gameService.updateGame(lastGame);
        StringBuilder wordIsBuild = new StringBuilder();
        StringBuilder wordsBuild = new StringBuilder();
        for (int i : indexs) {
            wordIsBuild.append(i).append(",");
        }
        wordIsBuild.deleteCharAt(wordIsBuild.length()-1);
        for (String value : maps.values()) {
            wordsBuild.append(value).append(",");
        }
        wordsBuild.deleteCharAt(wordsBuild.length()-1);
        user.setWordis(wordIsBuild.toString());
        user.setWords(wordsBuild.toString());
        user.setGameId(lastGame.getId());
        if (!isUpdate) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        response.setData(maps);
        return response;
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
                return"观众";
        }
        return null;
    }

}
