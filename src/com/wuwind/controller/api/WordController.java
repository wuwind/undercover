package com.wuwind.controller.api;

import com.wuwind.dao.bean.Word;
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

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(WordController.class);

    @Autowired
    WordService wordService;

    @RequestMapping("addWord")
    @ResponseBody
    public Word addWord(HttpServletRequest request, Word word) {
        System.out.println("word " + word.getW1());
        System.out.println("getParameter " + request.getParameter("w1"));
        Object add = wordService.add(word);
        return word;
    }

    @RequestMapping("getAllWords")
    @ResponseBody
    public List<Word> getAllWords(HttpServletRequest request) {
        return wordService.getAll();
    }

    @RequestMapping("getWordById")
    @ResponseBody
    public Word getWordById(Integer wordId) {
        return wordService.select(wordId);
    }
}
