package com.wuwind.dao.impl;

import com.wuwind.dao.bean.Game;
import com.wuwind.dao.GameDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoImpl extends BaseDaoImpl<Game> implements GameDao {

    @Override
    public List<Game> queryByRoomId(long roomId) {
        String sql = "select * from " + tbNmae + " where roomId = " + roomId+" and (finish is NULL or finish<>1)";
        return queryList(sql);
    }
}
