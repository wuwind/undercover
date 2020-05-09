package com.wuwind.controller;

import com.wuwind.bean.Game;
import com.wuwind.bean.Word;
import com.wuwind.service.GameService;
import com.wuwind.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    GameService gameService;
    @Autowired
    WordService wordService;

    @RequestMapping("main")
    public ModelAndView getAllWords(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        System.out.println("main");
        List<Game> all = gameService.getAll();
        Game game = all.get(all.size() - 1);
        Word word = wordService.select(game.getWordId());
        modelAndView.addObject("game", game);
        modelAndView.addObject("word", word);
        return modelAndView;
    }
}
