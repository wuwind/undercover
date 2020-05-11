package com.wuwind.controller.api;

import com.wuwind.bean.Game;
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
}
