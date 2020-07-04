package com.wuwind.controller.api;

import com.wuwind.dao.bean.Advise;
import com.wuwind.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class AdviseController {

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(AdviseController.class);

    @Autowired
    AdviseService adviseService;

    @RequestMapping("addAdvise")
    @ResponseBody
    public Advise addAdvise(HttpServletRequest request, Advise advise) {
//        System.out.println("advise " + advise.getW1());
//        System.out.println("getParameter " + request.getParameter("w1"));
        Object add = adviseService.add(advise);
        if (null == add)
            return null;
        advise.setId(((Number) add).longValue());
        return advise;
    }

    @RequestMapping("getAllAdvises")
    @ResponseBody
    public List<Advise> getAllAdvises(HttpServletRequest request) {
        return adviseService.getAll();
    }

    @RequestMapping("getAdviseById")
    @ResponseBody
    public Advise getAdviseById(Integer adviseId) {
        return adviseService.select(adviseId);
    }

    @RequestMapping("getAdviseRandom")
    @ResponseBody
    public Advise getAdviseRandom() {
        List<Advise> all = adviseService.getVisibleAll();
        if (all == null || all.size() < 1)
            return null;
        int index = (int)(Math.random() * all.size());
        return all.get(index);
    }

    @RequestMapping("setVisibleAdvises")
    @ResponseBody
    public Advise setVisibleAdvises(int id, int visible) {
        Advise advise = adviseService.select(id);
        if(null != advise) {
            advise.setVisible(visible);
            adviseService.update(advise);
        }
        return advise;
    }
}
