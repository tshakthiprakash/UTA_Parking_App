<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><script type="text/javascript">
function errordisplay()
{
	var mode = '${modess}';
	if(mode === 'error'){
	document.getElementById("errordata").style.display='block';
	documment.getElementById("successmsg").style.display='none';
	}
	if(mode === 'success'){
		document.getElementById("errordata").style.display='none';
		documment.getElementById("successmsg").style.display='block';
	}
}


</script>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activate User</title>
</head>
<body onload="errordisplay();">
<table>
<tr>
<td class="tabcontent"><a href='adminHomePageController'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>Activate User</h1>
<form action="userStatusController" method="get">
<table>
<tr>
<td>User name:</td><td><input type="text" id="search_username" name="search_username"></td>
<td id="errordata" style="color:red;display:none;">${UserStatuserrorMessage.usernameError}</td></tr>
<tr><td id="successmsg">${successmessage}</td></tr>
</table>
<br/>
<tr><td><input type="submit" id="Activate" value="Activate"></td></tr>
</form>
</body>
</html>