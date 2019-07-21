<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function editmode()
{
document.getElementById("edit").style.display='none';
var capacity = document.getElementsByClassName("capacity");
//var type = document.getElementsByClassName("type");
var update = document.getElementsByClassName("update");
//var drop = document.getElementsByClassName("dropdown");
length = capacity.length;
for(var i=0;i<length;i++)
	{
	capacity[i].disabled = false;
	update[i].style.display='block';
	}
}

function display_edit()
{
	var check = ${onloads};
	var mode = '${mode}';
	if(mode === 'addpark')
		document.getElementById("add_parking").style.display = 'block';
	if(mode === 'updatepark'){
		document.getElementById("display_modify").style.display = 'block';
		}
	if(mode === 'changename')
		document.getElementById("change_name").style.display = 'block';	
	if(check == 1)
	document.getElementById("edit").style.display='block';
}

function display_add()
{
document.getElementById("add_parking").style.display = 'block';
document.getElementById("modifyparkingarea").style.display = 'none';
document.getElementById("change_name").style.display = 'none';
}

function display_modify()
{
	document.getElementById("add_parking").style.display = 'none';
	document.getElementById("modifyparkingarea").style.display = 'block';
	document.getElementById("change_name").style.display = 'none';
}
function display_namechange()
{
	document.getElementById("add_parking").style.display = 'none';
	document.getElementById("modifyparkingarea").style.display = 'none';
	document.getElementById("change_name").style.display = 'block';	
	document.getElementById("display_modify").style.display = 'none';
}
</script>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
</head>
<body onload="display_edit();">
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td>  
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<button id="AddParkingAreabtn" style="margin-left:50px;" onclick="display_add();">Add Parking Area</button>
<button id="modifyParkingbtn" style="margin-left:50px;" onclick="display_modify();">Modify Parking Area</button>
<button id="changeNameBtn" style="margin-left:50px;" onclick="display_namechange();">Change Name of Parking Area</button>
<div id="add_parking" style="display:none;">
<form action="parkingspotController?action=addparking" method="post">
<table>
<tr><td>Parking Area Name:</td><td><input id="park_name" type="text" name="park_name"></td><td> <input id="parknameerror" name="parknameerror" value="<c:out value='${addparkError.getParkingareaNameError()}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td><tr>
<tr><td>Floor</td><td><input type="text" id="park_floor" name="park_floor"></td><td> <input name="floorerror" id="floorerror" value="<c:out value='${addparkError.getFloorError()}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td><tr>
<tr><td>Capacity</td><td><input type="text" name="park_cap" id="park_cap"></td><td> <input name="caperror" id="caperror" value="<c:out value='${addparkError.getCapacityError()}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td><tr>
<tr><td>Type:</td><td><select name="park_type" id="park_type">
<option id="Basic">Basic</option>
<option id="Premium">Premium</option>
<option id="Midrange">Midrange</option>
<option id="Access">Access</option></select></td></tr>
<tr><td><input id="Add" type="submit" value="Add">
</table>
</form>
</div>
<div id="change_name" style="display:none;">
<form action="parkingspotController?action=changename" method="post" onsubmit="return confirm('Confirmation required');" >
<table>
<tr><td>Parking Area Name:</td><td><select id="oldname" name="oldname" onchange="callnames();">
<c:forEach items="${modifyparkingAreaNames}" var="parkingareaname">
<option id="parkingareanameopt" value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td>New Name:</td><td><input type="text" id="newname" name="newname"/></td>
<td> <input id="changenameerror" name="changenameerror" value="<c:out value='${changenameerror}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"></td></tr>
<tr><td><input  id="change" type="submit" value="Change" ></td></tr> 
</table>
</form>
</div>
<div id="modifyparkingarea" style="display:none;">
<form action="parkingspotController?action=modifyparkingareas" method="post">
<table>
<tr><td>Parking Area Name:</td><td><select id="parkingareaname" name="parkingareaname" onchange="callnames();">
<c:forEach items="${modifyparkingAreaNames}" var="parkingareaname">
<option id="parkingareanameopt1" value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td><input  id="search" type="submit" value="Search" ></td></tr> 
</table>
</form>
</div>
<div id="display_modify">
<c:forEach items="${parkinfo}" var="modifypark">
<form action="parkingspotController?action=updateparkingarea&parkingname=${modifypark.parkingarea_name}" method="post">
<table>
<tr><td>Floor:</td><td><input id="floor" name="floor" type="text" value='${modifypark.floor}' READONLY style="border:none;"></td>
<td>Type:</td><td><input id="parkingtype" name="type" type="text" class="type" value='${modifypark.parkingtype}' READONLY style="border:none;"><td>
<td>Capacity:</td><td><input id="capacity" name="capacity" class="capacity" type="text" value='${modifypark.capacity}' disabled></td>
<td><input class="update" id="update" type="submit" value="Update" style="display:none;"></td></tr>
</table>
</form>
</c:forEach>
<input name="updateerror" id="updateerror" value="<c:out value='${updateparkingerror}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
<button id="edit" onclick="editmode();" style="display:none;">Edit</button>
</div>
</body>
</html>