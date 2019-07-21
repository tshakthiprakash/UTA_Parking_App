<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

input#ip01, input#ip02, input#ip03, input#ip04, input#ip05, input#ip06, input#ip07 {
	border: none;
	border-color: transparent;
 }
 
table td {
    overflow: hidden;
    white-space: nowrap;
}

</style>
</head>
<body>

<table>
<tr>
<c:if test="${'Student/Faculty'==loggedinuserrole}">
<td class="tabcontent"><a href='stuFacHomePageController'>Home</a></td> 
</c:if>
<c:if test="${'Manager'==loggedinuserrole}">
<td class="tabcontent"><a href='managerHomePageController'>Home</a></td> 
</c:if>
<td class="tabcontent"><a href=searchparkingspot.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>

<c:set var="rowno" value="${param.radioButton-1}"></c:set>
<c:set var="reservationid" value="${param.reservationid}"></c:set>
<c:set var="username" value="${param.username}"></c:set>


<form action='reserveParkingspotController' method='post'>
<h3>Reservation Details : </h3>
	
	<input id="reservationid" name="reservationid" style="display:none" value="${reservationid}"/>
	<input id="username" name="username" style="display:none" value="${username}"/>
	<input id="parkingareaid" name="parkingareaid" style="display:none" value="${parkingspots[rowno].parkingarea_id}"/>
	<table border="1" cellpadding="2" style="margin-left: 30px">
	     
		 <tr><td>Parking Area:</td><td><input readonly="readonly" type="text" id="ip01" name="parkingareaname" value="<c:out value='${parkingspots[rowno].parkingarea_name}'/>" ></td></tr>
		 <tr><td>Parking type:</td><td><input readonly="readonly"  border="0" type="text" id="ip02" name="parkingtype" value="<c:out value='${parkingspots[rowno].parkingtype}'/>" ></td></tr>
		 <tr><td>Floor:</td><td><input readonly="readonly"  border="0" type="text" id="ip03" name="parkingareafloor" value="<c:out value='${parkingspots[rowno].floor}'/>" ></td><tr>
		 <tr><td>Reservation start time:</td><td><input readonly="readonly"  border="0" type="text" id="ip04" name="reservationfromtime" value="<c:out value='${reservationfromtime}'/>" ></td><tr>
		 <tr><td>Reservation end time:</td><td><input readonly="readonly"  border="0" type="text" id="ip05" name="reservationtotime" value="<c:out value='${reservationtotime}'/>" ></td><tr>
		 <tr><td>Options selected:</td><td><input readonly="readonly"  border="0" type="text" id="ip06" name="selectedoptions" value="<c:out value='${selectedoptions}'/>" ></td><tr>
		 <tr><td>Total price:</td><td><input readonly="readonly"  border="0" type="text" id="ip07" name="totalcost" value="<c:out value='${totalcost}'/>" ></td><tr>
	</table>
	
	<input type="checkbox" id="selectedcart" name="selectedcart" style="display:none; margin-top: 8px; " ${selectedcart} >
 	<input type="checkbox" id="selectedcamera" name="selectedcamera" style="display:none; margin-top: 8px; " ${selectedcamera}>
 	<input type="checkbox" id="selectedhistory" name="selectedhistory" style="display:none; margin-top: 8px; " ${selectedhistory}>
	<c:choose>
		<c:when test = "${totalcost.equals('0.00')}">	
			<input type="submit" name="confirmreservation" id="confirmreservation" style="width: 80px; margin-left: 120px; margin-top: 10px;" value="Reserve"/>
		</c:when>
		<c:otherwise>
			<input type="submit" name="makepayment" id="makepayment" style="width: 150px; margin-left: 100px; margin-top: 10px;" value="Make Payment"/>
		</c:otherwise>
	</c:choose>
	
</form>


<br/>
</body>
</html>