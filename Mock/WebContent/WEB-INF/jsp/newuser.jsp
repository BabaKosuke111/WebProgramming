<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ新規登録</title>
</head>
<body>
<h1>ユーザ新規登録</h1>
${errMsg }
<form action="/Mock/NewLoginServlet" method="post" >
ログインID<input type="text" name="loginId"><br>
パスワード<input type="password" name="password"><br>
パスワード(確認)<input type="password" name="cfpassword"><br>
ユーザ名<input type="text" name="name"><br>
生年月日<input type="date" name="birthDate"><br>
<input type="submit"value="登録"><br>
<a href="UserListServlet">戻る</a>
</form>
</body>
</html>