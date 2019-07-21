<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User Results</title>
</head>
<body>
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td class="tabcontent"><a href=search_user.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>User List</h1>
<p>Click on user name to view user details</p>
<form action="searchUserController" method="get">
<table border=1 cellspacing="0">
<tr>
<th>First Name </th>
<th>Last Name </th>
<th>User Name </th>
</tr>

<c:forEach items="${userList}" var="search_user">
        <tr>
            <td id="fname"><c:out value="${search_user.getFirstname()}" /></td>
            <td id="lname"><c:out value="${search_user.getLastname()}" /></td>
            <td id="userName">
            	<a href="searchSpecificUserController?search_username=${search_user.getUsername()}">
            		<c:out value="${search_user.getUsername()}" />
            	</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>