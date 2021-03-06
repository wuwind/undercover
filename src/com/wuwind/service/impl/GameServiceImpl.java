package com.wuwind.service.impl;

import com.wuwind.dao.bean.Game;
import com.wuwind.dao.GameDao;
import com.wuwind.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Resource
    GameDao gameDao;

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    @Override
    public Object addGame(Game game) {
        return gameDao.add(game);
    }

    @Override
    public int updateGame(Game game) {
        return gameDao.update(game);
    }

    @Override
    public Game getGameById(long id) {
        return gameDao.queryById(id);
    }

    @Override
    public List<Game> getAllByRoomId(long roomId) {
        return gameDao.queryByRoomId(roomId);
    }
}
