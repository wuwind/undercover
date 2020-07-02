package com.wuwind.controller.api;

import com.wuwind.controller.bean.response.GameResponse;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;
    @Autowired
    WordService wordService;
    @Autowired
    RoomService roomService;

    @RequestMapping("games")
    @ResponseBody
    public List<Game> getGames() {
        return gameService.getAll();
    }

    @RequestMapping("addGame")
    @ResponseBody
    public Game addGame(HttpServletRequest request, Game game) {
        gameService.addGame(game);
        return game;
    }

    @RequestMapping("getLastWord")
    @ResponseBody
    public Game getLastWord(HttpServletRequest request) {
        System.out.println("getLastWord");
        List<Game> all = gameService.getAll();
        Game game = all.get(all.size() - 1);
        return game;
    }

    @RequestMapping("finish")
    @ResponseBody
    public Game finish(Long gameId, int finish) {
        Game game = gameService.getGameById(gameId);
        game.setFinish(finish);
        gameService.updateGame(game);
        return game;
    }

    @RequestMapping("updateGame")
    @ResponseBody
    public Game update(Game mGame) {
        System.out.println(mGame.toString());
        if (null != mGame && null != mGame.getId() && null != gameService.getGameById(mGame.getId())) {
            gameService.updateGame(mGame);
        }
        return mGame;
    }

    @RequestMapping("getGameByUser")
    @ResponseBody
    public Response getGameByUser(int[] userIds) {
        Response response = new Response();
        if (userIds == null || userIds.length < 1) {
            response.setCode(0);
            response.setMsg("没有填写userIds");
            return response;
        }
        //roomId
        User user0 = userService.getUserById(userIds[0]);
        if (null == user0) {
            response.setCode(0);
            response.setMsg("你还未加入房间");
            return response;
        }
        Room mRoom = roomService.select(user0.getRoomId());
        if (null == mRoom) {
            response.setCode(0);
            response.setMsg("没有找到你的房间");
            return response;
        }
        if (mRoom.getOpen() != 1) {
            response.setCode(0);
            response.setMsg("你的房间已关闭");
            return response;
        }
        //games
        List<Game> games = gameService.getAllByRoomId(mRoom.getId());
        if (null == games || games.isEmpty()) {
            response.setCode(1);
            response.setMsg("添加游戏中，请稍后刷新");
            return response;
        }
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Game game : games) {
            //words
            Word word = wordService.select(game.getWordId());
            if (word == null)
                continue;
            GameResponse gameResponse = new GameResponse();
            gameResponse.gameId = game.getId();
            //user
            for (int userId : userIds) {
                User user = userService.getUserById(userId);
                if (user == null)
                    continue;
                Integer gameNo = user.getGameNo();
                if(gameNo == null)
                    continue;
                String wordStr = getWord(game.getSequence(), gameNo, word);
                GameResponse.UserWord userWord = new GameResponse.UserWord();
                userWord.userId = userId;
                userWord.userName = user.getUsers();
                userWord.word = wordStr;
                gameResponse.userWords.add(userWord);
            }
            gameResponses.add(gameResponse);
        }
        response.setCode(1);
        response.setData(gameResponses);
        return response;
    }

    private String getWord(String sequenceStr, int index, Word word) {
        byte[] sequence = StrConverter.toByteArray(sequenceStr);
        if(index >= sequence.length || index < 0)
            return "";
        switch (sequence[index]) {
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
