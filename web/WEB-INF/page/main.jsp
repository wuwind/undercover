<%@ page import="com.wuwind.bean.Game" %>
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <%@page contentType="text/html;charset=utf-8" language="java" %>
    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Title</title>

    <style>
        /*假设的设备  320px 414px 640px */
        @media (min-width: 320px) {
            html {
                font-size: 50px;
            }
        }

        @media (min-width: 414px) {
            html {
                font-size: 64.6875px;
            }
        }

        @media (min-width: 640px) {
            html {
                font-size: 100px;
            }
        }

        body {
            margin: 0;
            padding: 0;
            font-size: 14px;
        }

        /*流式容器*/
        header {
            width: 100%;
            height: 1rem;
            line-height: 1rem;
            font-size: 0.32rem;
            text-align: center;
            background: green;
            color: #fff;
        }
    </style>
</head>
<body>

<!--1.伸缩布局   flex -->
<!--2.流式布局  百分比 -->
<!--3.响应布局  媒体查询 （超小屏设备的时候：流式布局）-->
<!--共同点：元素只能做宽度的适配（排除图片）-->
<!--适配方案rem：宽度和高度都能做到适配（等比缩放）-->
<!--4.rem布局-->
<!--通过控制html上的字体大小去控制页面上所有已rem为单位的基准值控制尺寸-->
<!--4.1 把页面上px单位转换成rem单位-->
<!--4.2 页面制作的时候 psd 上的量取的px转成rem使用-->
<!--4.3 怎么换算？？？预设一个基准值 方便计算 100px -->
<!--4.4 适配的时候设置基准值  320px  50px 怎么算：（640px 100px ===320px 50px）-->
<!--4.5 换算公式：当前rem基准值 = 预设的基准值 / 设计稿宽度 * 当前设备的宽度 -->
<!--4.6 怎么去改变 （js换算，媒体查询推荐）-->
<%--<header>main</header>--%>
<%--request:${game.sequence.toCharArray()}<br/>--%>

<c:forEach var="x" begin="1" end="${fn:length(game.sequence.toCharArray())}" step="1">
    <button type="button" style="width: 25%;padding: 10px;margin: 10px"
            onclick="show(${x})"
    >${x}号
    </button>
</c:forEach>
<br>
<c:forEach var="item" items="${game.sequence.toCharArray()}">

    <c:choose>
        <c:when test="${item == 0}">
            0
        </c:when>
        <c:when test="${item == 1}">
            1
        </c:when>
        <c:when test="${item == 2}">
            2
        </c:when>
        <c:when test="${item == 3}">
            3
        </c:when>
    </c:choose>
</c:forEach>


<script>
    function show(x)    //创建函数
    {
        var item = "${game.sequence}"
        var s = item[x-1]
        var res;
        if (s == 0) {
            res = "${word.w1}"
        } else if (s == 1) {
            res = "${word.w2}"
        } else if (s == 2) {
            res = "白板"
        } else if (s == 3) {
            res = "观众"
        }
        alert(res);    //弹出窗口
    }
</script>

</body>
</html>