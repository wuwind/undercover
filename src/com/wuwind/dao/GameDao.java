package com.wuwind.dao;


import com.wuwind.dao.bean.Game;

import java.util.List;

public interface GameDao extends BaseDao<Game> {

    List<Game> queryByRoomId(long roomId);

}
