package com.wuwind.controller.api;

import com.wuwind.bean.Word;
import com.wuwind.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class WordController {

    @Autowired
    WordService wordService;

    @RequestMapping("addWord")
    @ResponseBody
    public Word addWord(HttpServletRequest request, Word word) {
        System.out.println("word "+word.getW1());
        System.out.println("getParameter "+request.getParameter("w1"));
        Object add = wordService.add(word);
        return word;
    }

    @RequestMapping("getAllWords")
    @ResponseBody
    public List<Word> getAllWords(HttpServletRequest request) {
        System.out.println("getAllWords");
        List<Word> all = wordService.getAll();
        return all;
    }
}
