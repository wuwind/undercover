package com.wuwind.controller.api;

import com.wuwind.dao.bean.Activity;
import com.wuwind.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class ActivityController {

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(ActivityController.class);

    @Autowired
    ActivityService activityService;

    @RequestMapping("addActivity")
    @ResponseBody
    public Activity addActivity(HttpServletRequest request, Activity activity) {
//        System.out.println("activity " + activity.getW1());
//        System.out.println("getParameter " + request.getParameter("w1"));
        Object add = activityService.add(activity);
        if (null == add)
            return null;
        activity.setId(((Number) add).longValue());
        return activity;
    }

    @RequestMapping("getAllActivitys")
    @ResponseBody
    public List<Activity> getAllActivitys(HttpServletRequest request) {
        return activityService.getAll();
    }

    @RequestMapping("getActivityById")
    @ResponseBody
    public Activity getActivityById(Integer activityId) {
        return activityService.select(activityId);
    }

    @RequestMapping("getActivityRandom")
    @ResponseBody
    public Activity getActivityRandom() {
        List<Activity> all = activityService.getVisibleAll();
        if (all == null || all.size() < 1)
            return null;
        int index = (int)(Math.random() * all.size());
        return all.get(index);
    }

    @RequestMapping("setVisibleActivitys")
    @ResponseBody
    public Activity setVisibleActivitys(int id, int visible) {
        Activity activity = activityService.select(id);
        if(null != activity) {
            activity.setVisible(visible);
            activityService.update(activity);
        }
        return activity;
    }
}
