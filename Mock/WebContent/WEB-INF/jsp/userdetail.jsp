<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>ユーザ情報詳細参照</title>
</head>
<body>
<h1>ユーザ情報詳細参照</h1>
${userInfo.name }さん<br>
<a href="/Mock/LogoutServlet">ログアウト</a>
<form action="">
ログインID　${userDetail.loginId}<br>
ユーザ名　${userDetail.name }<br>
生年月日　${userDetail.birthDate }<br>
登録日時　${userDetail.createDate }<br>
更新日時　${userDetail.updateDate }<br>
<a href="UserListServlet">戻る</a>
</form>
</body>
</html>