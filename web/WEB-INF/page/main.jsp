<%@ page import="com.wuwind.dao.bean.Game" %>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <%@page contentType="text/html;charset=utf-8" language="java" %>
    <!--引入外部的css文件-->
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <!--引入外部的js文件-->
    <script type="text/javascript" src="js/main.js" />

    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>Title</title>

    <script>

    </script>
</head>
<body>


<c:forEach var="x" begin="1" end="${fn:length(game.sequence.toCharArray())}" step="1">
    <button type="button" style="width: 25%;padding: 10px;margin: 10px"
<%--            onclick="show(${x}, '${game.sequence}', '${word.w1}', '${word.w2}')"--%>
            onclick="showPopup(${x}, getWord(${x}, '${game.sequence}', '${word.w1}', '${word.w2}'))"
    >${x}号
    </button>
</c:forEach>
<br>
<!--弹窗点击除确定以外的取消和其他区域时弹窗都会消失且输出false，点击确定时为true-->
<div class="popup" id="popup">
    <div class="popup-content">
        <div class="top">
            <p>提示信息</p>
        </div>
        <div class="info">
            <p id="msg_notify">确认打开</p>
        </div>
        <div class="btn">
            <!--因为两个按钮在popup这个大框里，点击确定和取消就会同时点击父元素，
            会产生事件冒泡（即点击确定，会同时出现true和false）-->
            <button class="btn1" id="btn1" type="button" onclick="hidePopup(true)">确定</button>
            <button class="btn2" type="button" onclick="hidePopup(false)">取消</button>
        </div>
    </div>
</div>

</body>
</html>