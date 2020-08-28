package com.wuwind.controller;

import com.wuwind.controller.bean.response.VoteResponse;
import com.wuwind.dao.bean.Game;
import com.wuwind.dao.bean.Vote;
import com.wuwind.dao.bean.Word;
import com.wuwind.service.GameService;
import com.wuwind.service.VoteService;
import com.wuwind.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    GameService gameService;
    @Autowired
    WordService wordService;
    @Autowired
    VoteService voteService;

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

    @RequestMapping("vote/edit")
    public String getVoteEdit(HttpServletRequest request) {
        return "vote_edit";
    }

    @RequestMapping("vote/add")
    public String voteAdd(HttpServletRequest request, Vote vote) {
        String countStr = "";
        int len = vote.getItems().split(",").length;
        for (int i = 0; i < len; i++) {
            countStr += "0";
            if (i < len - 1) {
                countStr += ",";
            }
        }
        vote.setCounts(countStr);
        Object add = voteService.add(vote);
        return "redirect:/vote/" + add.toString();
    }

    @RequestMapping("vote/{id}")
    public ModelAndView getVote(HttpServletRequest request, @PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vote");
        Vote vote = voteService.select(id);
        System.out.println(vote.toString());
        modelAndView.addObject("vote", new VoteResponse(vote));
        return modelAndView;
    }

    @RequestMapping("vote/vote")
    public ModelAndView increaseVote(HttpServletRequest request, String id, String key) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vote");
        request.getSession().setAttribute("token", new Date().getTime());
        Vote vote = voteService.select(id);
        String[] items = vote.getItems().split(",");
        String[] counts = vote.getCounts().split(",");
        String countStr = "";
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(key)) {
                int c = Integer.parseInt(counts[i]) + 1;
                countStr += c;
            } else {
                countStr += counts[i];
            }
            if (i < items.length - 1) {
                countStr += ",";
            }
        }
        vote.setCounts(countStr);
        voteService.update(vote);
        System.out.println(vote.toString());
        modelAndView.addObject("vote", new VoteResponse(vote));
        return modelAndView;
    }

}
