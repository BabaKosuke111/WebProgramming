<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
</head>
<body>
<h1>ユーザ情報更新</h1>
<font color="red">${errMsg }</font>
<form action="UserUpdateServlet" method="post">
<input type="hidden"  name="targetId" value="${userDetail.id}">

${userInfo.name }さん<br>
<a href="/Mock/LogoutServlet">ログアウト</a><br>
ログインID　${userDetail.loginId}<br>
パスワード<input type="password" name="password"><br>
パスワード(確認)<input type="password" name="cfpassword"><br>
ユーザ名<input type="text" name="name" value="${ userDetail.name}"><br>
生年月日<input type="date" name="birthDate" value="${userDetail.birthDate }"><br>
<input type="submit"value="更新"><br>
</form>
<a href="UserListServlet">戻る</a>
</body>
</html>