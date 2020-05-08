package com.wuwind.controller;

import com.wuwind.bean.Game;
import com.wuwind.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
