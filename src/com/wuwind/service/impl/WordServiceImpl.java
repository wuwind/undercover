package com.wuwind.service.impl;

import com.wuwind.dao.bean.Word;
import com.wuwind.dao.WordDao;
import com.wuwind.service.WordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Resource
    WordDao wordDao;

    @Override
    public Object add(Word word) {
        return wordDao.add(word);
    }

    @Override
    public int delete(Word word) {
        return wordDao.delete(word);
    }

    @Override
    public int update(Word word) {
        return wordDao.update(word);
    }

    @Override
    public Word select(Object wordId) {
        return wordDao.queryById(wordId);
    }

    @Override
    public List<Word> getAll() {
        return wordDao.getAll();
    }
}
