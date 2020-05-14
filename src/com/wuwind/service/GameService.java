package com.wuwind.service;

import com.wuwind.dao.bean.Game;

import java.util.List;

public interface GameService {

    List<Game> getAll();

    Object addGame(Game game);

    int updateGame(Game game);
}
