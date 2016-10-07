<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/PetShop/login" method="post">
		Username: <input type="text" name="username"><br>
		Password: <input type="password" name="password"><br>
		Remember me <input type="checkbox" name="remember-me"> <br> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>