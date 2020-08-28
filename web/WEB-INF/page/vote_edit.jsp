<%--
  Created by IntelliJ IDEA.
  User: wuhf
  Date: 2020/8/28
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">
<head>
    <title>Title</title>
    <form action="<%=request.getContextPath()%>/vote/add" method="get">
        <p>title:</p> <input type="text" name="title"/>
        <p>items(逗号隔开):</p> <textarea name="items" rows="10"></textarea>
        <p><input type="submit" value="Submit"/></p>
    </form>
</head>
<body>

</body>
</html>
