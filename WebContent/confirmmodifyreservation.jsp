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

input#ip01 {
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

<td class="tabcontent"><a href='stuFacHomePageController'>Home</a></td> 
<td class="tabcontent"><a href=searchparkingspot.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>

<c:set var="rowno" value="${param.radioButton-1}"></c:set>
<c:set var="reservationid12" value="${param.reservationid}"></c:set>
<c:set var="username" value="${param.username}"></c:set>

<h3>Confirm the reservation details to modify : </h3>

<form action='ReservedSpotsController' method='post'>

	<input id="reservationid1" name="reservationid1" style="display:none" value="${reservationid}"/>
	<input id="username" name="username" style="display:none" value="${reservationsforcancellationlist[rowno].username}"/>
	<input id="parkingareaid" name="parkingareaid" style="display:none" value="${parkingspots[rowno].parkingarea_id}"/>
	<table border="1" cellpadding="2" style="margin-left: 30px">
	     <tr><td>Reservation Id:</td><td>
	     <input readonly="readonly" type="text" id="ip01" name="reservationid" value="<c:out value='${reservationsforcancellationlist[rowno].getReservation_id()}'/>" ></td></tr>
		 <tr><td>Parking Area:</td><td>
		 <input readonly="readonly" type="text" id="ip02" name="parkingareaname" value="<c:out value='${reservationsforcancellationlist[rowno].getParkingarea_name()}'/>" ></td></tr>
		 <tr><td>Parking type:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip03" name="parkingtype" value="<c:out value='${reservationsforcancellationlist[rowno].getParkingtype()}'/>" ></td></tr>
		 <tr><td>Floor:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip04" name="parkingareafloor" value="<c:out value='${reservationsforcancellationlist[rowno].getFloor()}'/>" ></td><tr>
		 <tr><td>Reservation start time:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip05" name="reservationfromtime" value="<c:out value='${reservationsforcancellationlist[rowno].getFrom_time()}'/>" ></td><tr>
		 <tr><td>Reservation end time:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip06" name="reservationtotime" value="<c:out value='${reservationsforcancellationlist[rowno].getTo_time()}'/>" ></td><tr>
		 <tr><td>Parking Slot no:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip07" name="parkingslot" value="<c:out value='${reservationsforcancellationlist[rowno].getParkingslot_no()}'/>" ></td><tr>
		 <tr><td>cart:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip08" name="cart" value="<c:out value='${reservationsforcancellationlist[rowno].getCart()}'/>" ></td><tr>
		 <tr><td>Camera:</td><td>
		 <input readonly="readonly" type="text" id="ip09" name="camera" value="<c:out value='${reservationsforcancellationlist[rowno].getCamera()}'/>" ></td></tr>
		 <tr><td>History:</td><td>
		 <input readonly="readonly"  border="0" type="text" id="ip10" name="history" value="<c:out value='${reservationsforcancellationlist[rowno].getHistory()}'/>" ></td></tr>
	</table>
	
	<br/>
            <!-- <input name="action" value="modifymyreservation" type="hidden" style="width: 100px; margin-left: 30px;">
            <input name="modifymyreservation" id="modifymyreservation" type="submit" value="Modify" style="width: 100px; margin-left: 80px;" >
            <input name="action" value="cancelmyreservation" type="hidden" style="width: 100px; margin-left: 20px;">
            <input name="cancelmyreservation" id="cancelmyreservation" type="submit" value="Delete" style="width: 100px; margin-left: 20px;" > -->
            
            <!-- <input name="action" value="modifyreservation" type="hidden" style="width: 100px; margin-left: 0px;"/> -->
            <input name="action" id="modifymyreservation" value="Modify" type="submit" style="width: 170px; margin-left: 10px;" /> 
           <!--  <input name="action" value="deletereservation" type="hidden" style="width: 100px; margin-left: 0px;"/> -->
            <input name="action" id="cancelmyreservation" type="submit" value="Delete" style="width: 170px; margin-left: 10px;" />
	
</form>


<br/>
</body>
</html>