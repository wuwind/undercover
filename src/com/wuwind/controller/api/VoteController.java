package com.wuwind.controller.api;

import com.alibaba.fastjson.JSON;
import com.wuwind.controller.bean.VoteBean;
import com.wuwind.controller.bean.response.VoteResponse;
import com.wuwind.dao.bean.Properties;
import com.wuwind.dao.bean.User;
import com.wuwind.dao.bean.Vote;
import com.wuwind.dao.bean.VoteForm;
import com.wuwind.response.Response;
import com.wuwind.service.PropertiesService;
import com.wuwind.service.UserService;
import com.wuwind.service.VoteFormService;
import com.wuwind.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("api")
public class VoteController {

    //在需要使用日志的地方加上这句代码即可
//    private static Logger logger = Logger.getLogger(VoteController.class);

    @Autowired
    VoteService voteService;
    @Autowired
    VoteFormService voteFormService;
    @Autowired
    UserService userService;
    @Autowired
    PropertiesService propertiesService;


    @RequestMapping("addVoteItems")
    @ResponseBody
    public Object addVoteItems(HttpServletRequest request, String title, String[] items, Integer type, Integer userId,Integer properties) {
        System.out.println("title " + title);
        System.out.println("items " + Arrays.toString(items));
        Response response = new Response();
        if (null == userId) {
            response.setCode(0);
            response.setMsg("你没有权限添加");
            return response;
        }
        if (StringUtils.isEmpty(title)) {
            response.setCode(0);
            response.setMsg("添加内容");
            return response;
        }
        if (null == items || items.length <= 0 || StringUtils.isEmpty(items[0])) {
            response.setCode(0);
            response.setMsg("添加选项");
            return response;
        }
        List<VoteBean> data = new ArrayList<>();
        for (String item : items) {
            VoteBean bean = new VoteBean();
            bean.setItem(item);
            data.add(bean);
        }
        Vote vote = new Vote();
        vote.setItems(JSON.toJSONString(data));
        vote.setTitle(title);
        vote.setType(type);
        vote.setProperties(properties);
        vote.setCreateTime(new Date().getTime());
        vote.setUserId(userId);
        response.setMsg("添加成功");
        response.setCode(1);
        response.setData(voteService.add(vote));
        return response;
    }

    @RequestMapping("addVote")
    @ResponseBody
    public Vote addVote(HttpServletRequest request, Vote vote) {
        String countStr = "";
        int len = vote.getItems().split(",").length;
        for (int i = 0; i < len; i++) {
            countStr += "0";
            if (i < len - 1) {
                countStr += ",";
            }
        }
        vote.setCounts(countStr);
        Object add = voteService.add(vote);
        if (null == add)
            return null;
        vote.setId(((Number) add).longValue());
        return vote;
    }

    @RequestMapping("getAllVotes")
    @ResponseBody
    public List<Vote> getAllVotes(HttpServletRequest request, Integer userId) {
        if(null == userId)
            userId = 0;
        if (userId == 999) {
            return voteService.getAll();
        }
        return voteService.selectByUserId(userId);
    }

    @RequestMapping("getVoteById")
    @ResponseBody
    public VoteResponse getVoteById(Integer voteId) {
        Vote vote = voteService.select(voteId);
        if (null == vote) {
            return null;
        }
        VoteResponse voteResponse = new VoteResponse(vote);
        List<VoteForm> votes = voteFormService.getByVoteId(voteId);
        for (VoteForm voteForm : votes) {
            if (null == voteForm.getIdx() || voteForm.getIdx() >= voteResponse.getVotes().size())
                continue;
            VoteBean voteBean = voteResponse.getVotes().get(voteForm.getIdx());
            voteBean.setCount(voteBean.getCount() + 1);
        }
        Properties select = propertiesService.select(vote.getProperties());
        if (null == select) {
            select = propertiesService.select(1);
        }
        voteResponse.setProperties(select);
        return voteResponse;
    }

    @RequestMapping("vote")
    @ResponseBody
    public Response vote(Long voteId, Long userId, Integer idx) {
        Response response = new Response();
        Vote vote = voteService.select(voteId);
        if (null == vote) {
            response.setCode(0);
            response.setMsg("已取消");
            return response;
        }
        User user = userService.getUserById(userId);
        if (null == user) {
            response.setCode(-1);
            response.setMsg("请先登录");
            return response;
        }
        List<VoteForm> voteForms = voteFormService.getByUseId(userId);
        if (vote.getType() == null || vote.getType() == 0) {
            if (voteForms != null && !voteForms.isEmpty()) {
                for (VoteForm voteForm : voteForms) {
                    if (voteForm.getVoteId().longValue() == voteId) {
                        response.setCode(0);
                        response.setMsg("别重复");
                        return response;
                    }
                }
            }
        } else {
            for (VoteForm voteForm : voteForms) {
                if (voteForm.getIdx().intValue() == idx && voteForm.getVoteId().longValue() == voteId) {
                    response.setCode(0);
                    response.setMsg("别重复");
                    return response;
                }
            }
        }
        VoteForm voteForm = new VoteForm();
        voteForm.setVoteId(voteId);
        voteForm.setUserId(userId);
        voteForm.setIdx(idx);
        voteForm.setCreateTime(new Date().getTime());
        Object add = voteFormService.add(voteForm);
        response.setData(add);
        response.setMsg("Success");
        response.setCode(1);
        return response;
    }

}
