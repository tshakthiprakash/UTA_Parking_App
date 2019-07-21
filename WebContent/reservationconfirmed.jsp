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

input#ip01, input#ip02, input#ip03, input#ip04, input#ip05, input#ip06 {
	border: none;
	border-color: transparent;
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
<td><a href="LogoutController">Logout</a></td>
</tr></table>
<input id="parkingareaid" name="parkingareaid" style="display:none" value="${parkingareaid}"/>
<input id="parkingareaname" name="parkingareaname" style="display:none" value="${parkingareaname}"/>
<input id="selectedoptions" name="selectedoptions" style="display:none" value="${selectedoptions}"/>
<input id="parkingtype" name="parkingtype" style="display:none" value="${parkingtype}"/>
<input id="floor" name="parkingareafloor" style="display:none" value="${parkingareafloor}"/>
<input id="reservationfromtime" name="reservationfromtime" style="display:none" value="${reservationfromtime}"/>
<input id="reservationtotime" name="reservationtotime" style="display:none" value="${reservationtotime}"/>
<input id="reservation" name="reservation" style="display:none" value="${reservation}"/>
<input id="totalcost" name="totalcost" style="display:none" value="${totalcost}"/>
<h3>Your parking reservation confirmation details : </h3>
	<table border="1" cellpadding="2" style="margin-left: 30px">
		 <tr><td>Parking Area:</td><td><input type="text" id="ip01" name="parkingareaname" value="<c:out value='${parkingareaname}'/>" ></td></tr>
		 <tr><td>Parking type:</td><td><input type="text" id="ip02" name="parkingtype" value="<c:out value='${parkingtype}'/>" ></td></tr>
		 <tr><td>Floor:</td><td><input type="text" id="ip03" name="parkingareafloor" value="<c:out value='${parkingareafloor}'/>" ></td><tr>
		 <tr><td>Reservation start time:</td><td><input type="text" id="ip04" name="reservationfromtime" value="<c:out value='${reservationfromtime}'/>" ></td><tr>
		 <tr><td>Reservation end time:</td><td><input type="text" id="ip05" name="reservationtotime" value="<c:out value='${reservationtotime}'/>" ></td><tr>
		 <tr><td>Selected options:</td><td><input type="text" id="ip06" name="options" value="<c:out value='${selectedoptions}'/>" ></td><tr>
	</table>
</body>
</html>