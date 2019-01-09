<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ログイン画面</title>
</head>
<body>
<h1>ログイン画面</h1>
<font color="red">${errMsg }</font>
<form action="/Mock/LoginServlet" method="post">
ログインID<input type="text" name="loginId"><br>
パスワード<input type="password" name="password">
<br>
<input type="submit"value="ログイン">
</form>
</body>
</html>