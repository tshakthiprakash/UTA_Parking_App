<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<h1>Login page</h1>
<form action="loginUserController" method="post">
<table>
<tr>
<td>Username:</td><td><input type="text" id = "login_username" name="login_username"></td>
<td>
<input value="<c:out value='${incorrectpass}'/>" id = "password_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"></td></tr>
<tr>
<td>Password:</td><td><input type="password" id = "login_password" name="login_password"></td></tr>
<tr><td><input id="login_button" type="submit" value="Login"></td></tr>
</table>
<input value="<c:out value='${errorMessage}'/>" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> 
</form>

<a href="register_user.jsp">Register here !!</a>

</body>
</html>