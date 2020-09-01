package com.wuwind.controller.api;

import com.wuwind.controller.bean.response.VoteResponse;
import com.wuwind.dao.bean.User;
import com.wuwind.dao.bean.Vote;
import com.wuwind.dao.bean.VoteForm;
import com.wuwind.response.Response;
import com.wuwind.service.UserService;
import com.wuwind.service.VoteFormService;
import com.wuwind.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @RequestMapping("addVote")
    @ResponseBody
    public Vote addVote(HttpServletRequest request, Vote vote) {
//        System.out.println("vote " + vote.getW1());
//        System.out.println("getParameter " + request.getParameter("w1"));
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
    public List<Vote> getAllVotes(HttpServletRequest request) {
        return voteService.getAll();
    }

    @RequestMapping("getVoteById")
    @ResponseBody
    public VoteResponse getVoteById(Integer voteId) {
        Vote vote = voteService.select(voteId);
        if(null == vote) {
            return null;
        }
        List<VoteForm> votes = voteFormService.getByVoteId(voteId);
        String[] split = vote.getItems().split(",");
        int[] counts = new int[split.length];
        for (VoteForm voteForm : votes) {
            counts[voteForm.getIdx()]++;
        }
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            data.put(split[i], counts[i]+"");
        }
        VoteResponse voteResponse = new VoteResponse();
        voteResponse.setId(voteId.longValue());
        voteResponse.setTitle(vote.getTitle());
        voteResponse.setMap(data);
        return voteResponse;
    }

    @RequestMapping("vote")
    @ResponseBody
    public Response vote(Long voteId, Long userId, Integer index) {
        Response response = new Response();
        Vote vote = voteService.select(voteId);
        if (null == vote) {
            response.setCode(0);
            response.setMsg("投票已取消");
            return response;
        }
        User user = userService.getUserById(userId);
        if (null == user) {
            response.setCode(0);
            response.setMsg("请先登录");
            return response;
        }
        List<VoteForm> voteForms = voteFormService.getByUseId(userId);
        if (vote.getType() == null || vote.getType() == 0) {
            if (voteForms != null && !voteForms.isEmpty()) {
                for (VoteForm voteForm : voteForms) {
                    if(voteForm.getVoteId() == voteId) {
                        response.setCode(0);
                        response.setMsg("你已投过票");
                        return response;
                    }
                }
            }
        } else {
            for (VoteForm voteForm : voteForms) {
                if (voteForm.getIdx().intValue() == index && voteForm.getVoteId().longValue() == voteId) {
                    response.setCode(0);
                    response.setMsg("你已投过票");
                    return response;
                }
            }
        }
        VoteForm voteForm = new VoteForm();
        voteForm.setVoteId(voteId);
        voteForm.setUserId(userId);
        voteForm.setIdx(index);
        voteForm.setCreateTime(new Date().getTime());
        Object add = voteFormService.add(voteForm);
        response.setData(add);
        response.setCode(1);
        return response;
    }

}
