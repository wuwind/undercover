package com.wuwind.controller.api;

import com.wuwind.dao.bean.ActivityForm;
import com.wuwind.response.Response;
import com.wuwind.service.ActivityFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api")
public class ActivityFormController {

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(ActivityFormController.class);

    @Autowired
    ActivityFormService activityFormService;

    @RequestMapping("addActivityForm")
    @ResponseBody
    public Response addActivityForm(HttpServletRequest request, ActivityForm activityForm) {
//        System.out.println("activityForm " + activityForm.getW1());
//        System.out.println("getParameter " + request.getParameter("w1"));
        Response response = new Response();
        activityForm.setUserId(1l);
        activityForm.setActivityId(1l);
        if(activityForm.getUserId() == null || activityForm.getActivityId() == null){
            response.setCode(0);
            response.setMsg("user or activity is null");
            return response;
        }
        if(activityFormService.isExist(activityForm)){
            response.setCode(0);
            response.setMsg("你已报名");
            return response;
        }
        Object add = activityFormService.add(activityForm);
        if (null == add)
            return null;
        activityForm.setId(((Number) add).longValue());
        response.setCode(1);
        response.setData(add);
        return response;
    }

    @RequestMapping("getAllActivityForms")
    @ResponseBody
    public List<ActivityForm> getAllActivityForms(HttpServletRequest request) {
        return activityFormService.getAll();
    }

    @RequestMapping("getActivityFormById")
    @ResponseBody
    public ActivityForm getActivityFormById(Integer activityFormId) {
        return activityFormService.select(activityFormId);
    }

    @RequestMapping("getActivityFormRandom")
    @ResponseBody
    public ActivityForm getActivityFormRandom() {
        List<ActivityForm> all = activityFormService.getVisibleAll();
        if (all == null || all.size() < 1)
            return null;
        int index = (int)(Math.random() * all.size());
        return all.get(index);
    }

}
