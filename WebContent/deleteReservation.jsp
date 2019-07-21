<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table#t01 {
  border: 0.1px solid black;  
  align: "CENTER";
}
table#t01 tr:nth-child(even) {
  border: 0.1px solid black; 
  align: "CENTER";
  background-color: #AED6F1;
}
table#t01 tr:nth-child(odd) {
  border: 0.1px solid black; 
  align: "CENTER";
  background-color: #EBF5FB;
}
table#t01 th {
  color: black;
  align: "CENTER";
  border: 0.1px solid black;
  background-color: #EBF5FB;
}

.tabcontent {
  padding: 6px 12px;
}

input#ip01 {
	border: none;
	border-color: transparent;
 }
 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update or Delete User Reservation</title>
</head>
<body>
<table>
<tr>
<td class="tabcontent" id="managerHomeFromDelete"><a href='${home}'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>

<h1>Delete User Reservation</h1>
<form action="ReservedSpotsController" method="get">
<table border=0 cellspacing="0"><tr>
<td>User name:</td><td><input type="text" id="search_username" name="search_username" value="<c:out value='${search_username}'/>"></td>
<td id="usernameError" style="color:red;">${errorMessage.usernameError}</td></tr>
<tr></tr>

</table>
<br/>
<input name="action" value="SearchByUserName" type="hidden" style="width: 100px; margin-left: 30px;">
	<input name="SearchByUserName" id="SearchByUserName" type="submit" value="Search" style="width: 100px; margin-left: 30px;" >
	<input type="reset" id="reset" value="Reset" style="width: 100px; margin-left: 30px;">
</form>



<c:if test="${reservationsforcancellationlist=='none'}">
<br/>
<input name="AlertMsg" id="AlertMsg" value="<c:out value='Sorry! The user does not have reservations eligible for cancellation.'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:if>

<c:if test = "${reservationsforcancellationlist!='none' && !empty reservationsforcancellationlist}">
	
<form action="confirmmodifyreservationbymanager.jsp" name="table_form" id="table_form">

<table class="myTable" id="t01"> 
		<tr class="myTableRow" align="CENTER">		
				
			<th>Reservation id </th>
			<th>Parking Area </th>
			<th>Parking Type </th>
			<th>Floor </th>
			<th>Reservation Date </th>
			<th>From time </th>
			<th>To time </th>
			<th>Parking Slot Number </th>
			<th>Cart </th>
			<th>Camera </th>
			<th>History </th>
			<th>Select </th>
			<th style="border:none"></th>
		</tr>
		
		
 		<c:forEach items="${reservationsforcancellationlist}" var="item" varStatus="status">
 		
 		
 			<input type="text" id="reservationid" name="reservationid" style="display:none" value="<c:out value="${item.reservation_id}" />" >
			<input type="text" id="username" name="username" style="display:none" value="<c:out value="${item.getUsername()}" />" >
			<input type="text" id="parkingid" name="parkingid" style="display:none" value="<c:out value="${item.getParkingarea_id()}" />" > 		
			<tr class="myTableRow">			
			<td class="myTableCell" style="width: 145px;" align=CENTER >
					<c:out value="${item.getReservation_id()}" />
				</td>			
			<td class="myTableCell" style="width: 104px; " align=CENTER ><c:out value="${item.getParkingarea_name()}" /></td>
			<td class="myTableCell" style="width: 130px; " align=CENTER ><c:out value="${item.getParkingtype()}" /></td>
			<td class="myTableCell" style="width: 63px; " align=CENTER ><c:out value="${item.getFloor()}" /></td>
			<td class="myTableCell" style="width: 63px; " align=CENTER ><c:out value="${item.getReservation_date()}" /></td>
			<td class="myTableCell" style="width: 104px; " align=CENTER ><c:out value="${item.getFrom_time()}" /></td>
			<td class="myTableCell" style="width: 130px; " align=CENTER ><c:out value="${item.getTo_time()}" /></td>
			<td class="myTableCell" style="width: 63px; " align=CENTER ><c:out value="${item.getParkingslot_no()}" /></td>
			<td class="myTableCell" style="width: 63px; " align=CENTER ><c:out value="${item.getCart()}" /></td>
			<td class="myTableCell" style="width: 130px; " align=CENTER ><c:out value="${item.getCamera()}" /></td>
			<td class="myTableCell" style="width: 63px; " align=CENTER ><c:out value="${item.getHistory()}" /></td>
			<td> <input type="radio" id="radioButton" name="radioButton" value="${status.count}" onclick="check();">
				</td>
			</tr>
		</c:forEach>	
</table>
	<br/>
	<input type="submit" name="modifyButton" id="modifyButton" value="Proceed to Modify or Delete" style="margin-left: 285px;" disabled="disabled" ></input>	
</form>
</c:if>
<script>
 function check()
 {
 var ele = document.getElementsByName('radioButton');
 var flag=0;
 for(var i=0;i<ele.length;i++)
 {
     if(ele[i].checked)
      flag=1;

 } 
 if(flag==1){
 	document.getElementById('modifyButton').disabled=false;
 }
 }
 </script>
</body>
</html>