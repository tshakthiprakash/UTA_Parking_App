<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Violations</title>
</head>
<body>
<table>
<tr> 
<td class="tabcontent"><a href='${home}'>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>My Violations</h1>
<form action="MyViolationsController" method="get">
<table border=1 cellspacing="0">
<tr>
<th>User Name </th>
<th>No shows </th>
<th>Overdue </th>
</tr>
<tr>
<td id="uname">${username}</td>
<td id="noshows">${noshows}</td>
<td id="overdue">${overdue}</td>
</tr>
</body>
</html>