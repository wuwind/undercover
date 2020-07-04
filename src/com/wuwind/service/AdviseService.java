package com.wuwind.service;

import com.wuwind.dao.bean.Advise;

import java.util.List;

public interface AdviseService {

    Object add(Advise advise);

    int delete(Advise advise);

    int update(Advise advise);

    Advise select(Object adviseId);

    List<Advise> getAll();
    List<Advise> getVisibleAll();
}
