<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>ユーザ削除確認</title>
</head>
<body>
<h1>ユーザ削除確認</h1>
${userInfo.name }<br>
<a href="/Mock/LogoutServlet">ログアウト</a><br>
<form action="UserDeleteServlet" method="post">
<input type="hidden"  name="targetId" value="${userDetail.id}">
ログインID ${userDetail.loginId}<br>
を本当に削除してよろしいでしょうか。<br>
<a href="/Mock/UserListServlet">キャンセル</a>　　　　<input type="submit"value="OK"><br>
</form>
</body>
</html>