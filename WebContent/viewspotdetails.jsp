<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function display_unavailable()
{
	var displaymode = '${displayspotdeatil}';
	if(displaymode == 1){
		document.getElementById("parkspotdetails").style.display ='block';
	}
var check = '${isavailable}';
if(check == 1)
	{
	document.getElementById("spotunavailable").style.display='block';
	document.getElementById("spotdetails").style.display='none';
	}
var hidetable = '${hidereservationlist}';
if(hidetable == 1)
	{
	document.getElementById("spotdetails").style.display='none';
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view spot details</title>
</head>
<body onload="display_unavailable();">
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>

<form action="viewAvailSpotController?action=searchspotdetails" method="post">
<table>
<tr><td>Parking area name:</td><td><select id="parkingareaname" name="parkingareaname">
<c:forEach items="${parkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td>Permit type:</td><td><select id="type" name="type" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td></tr>
<tr><td>Spot No:</td><td><input id="spot" type="text" name="spotno"/></td>
<td> <input id="spotNoError" name="spotNoError" value="<c:out value='${spotdetailserror}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr><td><input id="search" type="submit" value="Search"/></td></tr>
</table>
</form>
<div id="parkspotdetails" style="display:none;">
<table>
<tr><td>Parking Area Name:</td><td id="name">${parknamedetail}</td></tr>
<tr><td>Permit type:</td><td id="detail">${typedetail}</td></tr>
<tr><td>Floor No:</td><td id="floor">${floor}</td></tr>
</table>
</div>
<div id="spotunavailable" style="display:none;"><p>SPOT is made unavailable</p></div>
<table id="spotdetails">
<tr><th>Username</th><th>From time</th><th>To time</th>
<c:forEach items="${spotdetailslist}" var="spotdetail">
<tr><td id="uname"><a href="searchSpecificUserController?search_username=${spotdetail.username}">${spotdetail.username}</a></td><td id="fromTime">${spotdetail.from_time}</td><td id="toTime">${spotdetail.to_time}</td></tr>
</c:forEach>
</table>
<p id="noreservationlist">${noreservationmsg}</p>
</body>
</html>
