package com.wuwind.controller.api;

import com.wuwind.dao.bean.Game;
import com.wuwind.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class GameController {

    @Autowired
    GameService gameService;

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
}
