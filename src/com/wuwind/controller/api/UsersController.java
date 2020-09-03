package com.wuwind.controller.api;

import com.alibaba.fastjson.JSONObject;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
    public List<User> getUsers(Integer roomId) {
        if (null == roomId) {
            return userService.getAll();
        }
        return userService.getAllByRoomId(roomId);
    }

    @RequestMapping("ready")
    @ResponseBody
    public int ready(String userId) {
        User user = userService.getUserById(Long.parseLong(userId));
        user.setReady(1);
        return userService.updateUser(user);
    }

    @RequestMapping("addWxUsers")
    @ResponseBody
    public synchronized Response addWxUsers(GameUser gUser) {
        Response response = new Response();
        if (gUser.getWxCode() == null) {
            response.setMsg("wx code is null");
            return response;
        }
        String openId = getopenid("wxa83fc82123d0f0fe", gUser.getWxCode(), "059942c06789f6e9b39a21d34b993eda");
        if (null == openId) {
            response.setMsg("get openid is null");
            return response;
        }
        JSONObject jsonObject = JSONObject.parseObject(openId);
        String openid = jsonObject.get("openid").toString();
        User u = new User();
        u.setUsers(gUser.getWxName());
        u.setWxId(openid);
        u.setReady(0);
        u.setWxName(gUser.getWxName());
        u.setWxPhoto(gUser.getWxPhoto());
        u.setuOut(0);
        u.setRoomId((long) gUser.getRoomId());
        u.setCreateTime(simpleDate.format(new Date()));
        Object id =  userService.addWxUser(u);
        if(null == id) {
            response.setMsg("insert error");
            return response;
        }
        response.setCode(1);
        response.setData(id);
        return response;
    }

    @RequestMapping("addUsers")
    @ResponseBody
    public synchronized Response addUsers(GameUser gUser) {
        System.out.println("addUsers " + gUser.toString());
        Response response = new Response();
        List<String> ids = new ArrayList<>();
        Room room = roomService.select(gUser.getRoomId());
        if (null == room) {
            response.setCode(0);
            response.setMsg("房间不存在");
            return response;
        }
//        if (room.getNum() >= room.getGameCount()) {
//            response.setCode(0);
//            response.setMsg("房间已人满");
//            return response;
//        }
        Integer roomNum = room.getNum();
        if (null == roomNum)
            roomNum = 0;
        for (String user : gUser.getUsers()) {
            List<User> userByName = userService.getUserByName(user);
            if (null != userByName) {
                for (User user1 : userByName) {
                    if (user1.getRoomId() == gUser.getRoomId()) {
                        response.setCode(0);
                        response.setMsg(user + " 昵称重复，换一个");
                        return response;
                    }
                }
            }
        }
        for (String user : gUser.getUsers()) {
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
            roomNum++;
        }
        room.setNum(roomNum);
        int update = roomService.update(room);
        if (update < 1) {
            response.setCode(0);
            response.setMsg("加入房间失败，请重试");
            return response;
        }
        response.setCode(1);
        response.setData(ids);
        return response;
    }

    public String getopenid(String appid, String code, String secret) {
        BufferedReader in = null;
//appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        try {
            URL weChatUrl = new URL(url);
// 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
// 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
// 建立实际的连接
            connection.connect();
// 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
// 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("addUser")
    @ResponseBody
    public synchronized Response addUser(HttpServletRequest request, GameUser gUser) {
        System.out.println("addUser " + gUser.toString());
        Response response = new Response();
        if (gUser.getWxCode() == null) {
            response.setMsg("wx code is null");
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

    @RequestMapping("getUsersByRoomId")
    @ResponseBody
    public List<User> getUsersByRoomId(int roomId) {
        if (roomId < 0)
            return null;
        return userService.getAllByRoomId(roomId);
    }

    @RequestMapping("delUserById")
    @ResponseBody
    public int delUserById(int userId) {
        return userService.delUserById(userId);
    }

    @RequestMapping("getPermission")
    @ResponseBody
    public String[] getPermission(Integer userId) {
//        if(userId!=999)
//            return null;
        return new String[]{"add_vote"};
    }

    @RequestMapping("getProperties")
    @ResponseBody
    public Map<String, String> getProperties(Integer userId) {
        Map<String, String> map = new HashMap<>();
        map.put("word", "");
        map.put("selectWord", "选词");
        map.put("showCount", "0");
        return map;
    }

}
