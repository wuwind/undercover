<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">
    <title>投票</title>
</head>
<!-- 这里使用了一张背景，注释掉了。
<body style="background-image:url(body.jpeg); background-size:100%; background-repeat: no-repeat;">
 -->
<body>

<div align="center">
    <h4>${vote.title}</h4>
    <table id="main">
        <c:forEach items="${vote.map}" var="item" varStatus="idxStatus">
            <tr>
                <td>
                    <input type="radio" name="option" checked="checked" value="${item.key}"/>${item.key}
                </td>
            </tr>
            <tr>
                <td style="width:550px">
                    <div id=${idxStatus.index} style="background-color:#acd6ff;width:${item.value*3}px;height:20px;border-width:0px;">
                    </div>
                </td>
                <td>
                    <label id="label${idxStatus.index}">${item.value}</label>票
                </td>
            </tr>

        </c:forEach>

        <tr>
            <td><p>
                <input type="submit" value="确认投票" onclick="vote()"/>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">

    //在每个投票选项后面写了个div，用div的宽度来代表当前该选项的投票数。
    function vote() {	//函数vote，当点击确认投票的时候，调用vote方法

        <%
        if(null !=  session.getAttribute("token")) {
        %>
        alert("不要重复投票");
        return;
        <%
        }
        %>

        //for循环的条件是，所有投票选项的个数。
        for (var i = 0; i < document.getElementsByName("option").length; i++) {
            //查找到是哪个投票选项被选中
            if (document.getElementsByName("option")[i].checked == true) {
                var width = document.getElementById(i).style.width;	//获取到当前选项的宽度。
                width = parseInt(width);//将宽度转化为int型，因为获取到的width的单位是px
                width += 3;//改变width的值，这里就是定义每次投票的进度条的增速
                document.getElementById(i).style.width = width + "px";//修改原div的宽度

                var label = "label" + i;//lable标签里面写的是当前的投票数目。
                var num = document.getElementById(label).innerText;//获取到当前的票数
                var key = document.getElementsByName("option")[i].value;//获取到当前的票数
                document.getElementById(label).innerText = ++num;//票数加1，并修改原值
                post("<%=request.getContextPath()%>/vote/vote", {"key": key, "id": ${vote.id}});
                alert("投票成功");
            }
        }
    }

    function post(url, params) {
        // 创建form元素
        var temp_form = document.createElement("form");
        // 设置form属性
        temp_form.action = url;
        temp_form.target = "_self";
        temp_form.method = "post";
        temp_form.style.display = "none";
        // 处理需要传递的参数
        for (var x in params) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = params[x];
            temp_form.appendChild(opt);
        }
        document.body.appendChild(temp_form);
        // 提交表单
        temp_form.submit();
    }

</script>
</html>
