<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
</head>
<body>
<a href="/Mock/LogoutServlet">ログアウト</a>
<h1>ユーザー覧</h1>
${userInfo.name }さん<br>
<a href="NewLoginServlet">新規登録</a>
<form action= "/Mock/UserListServlet" method="post">
ログインID<input type="text" name="login_id"><br>
ユーザー名<input type="text" name="na_me"><br>
生年月日<input type="date" name="startDate">～<input type="date" name="endDate"><br>
<input type="submit"value="検索">
<hr>
<table border="1" style="width:400">
<tr bgcolor="silver"><th>ログインID</th><th>ユーザ名</th><th>生年月日</th><th></th><tr>
<c:forEach var="user" items="${userList}">
<tr><td>${user.loginId}</td><td>${user.name}</td><td>${user.birthDate}</td>
<td><a href="UserDetailServlet?targetId=${user.id}">詳細</a>　　　
<c:if test="${userInfo.name==user.name||userInfo.name=='管理者'}" >
<a href="UserUpdateServlet?targetId=${user.id}">更新</a>　　　
</c:if>
<c:if test="${userInfo.name=='管理者'}" >
<a href="UserDeleteServlet?targetId=${user.id}">削除</a>
</c:if>
</td>
</c:forEach>
</tr>
</table>
</form>
</body>
</html>