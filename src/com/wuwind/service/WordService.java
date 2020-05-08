package com.wuwind.service;

import com.wuwind.bean.Word;

import java.util.List;

public interface WordService {

    Object add(Word word);

    int delete(Word word);

    int update(Word word);

    int select(Word word);

    List<Word> getAll();
}
