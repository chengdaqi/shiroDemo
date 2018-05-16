<%--
  Created by IntelliJ IDEA.
  User: 30378
  Date: 2018/5/16
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <h1>欢迎登录</h1>
    <form action="/userLogin">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="submit" value="提交">
    </form>
</body>
</html>
