<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<script type="text/javascript">
function openpage(url)
{
window.location.href = url;	
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student/Faculty Home page</title>
</head>
<body>
<h1>Home page</h1>
<a href="ProfileController">My Profile</a><br>
<a href="parkingspotController?action=searchparkingspotload">Search Parking Spot</a><br>
<form name="viewReservationsForm" id="viewReservationsForm" action="ReservedSpotsController" method ="get">
	<a href="#" onclick="document.getElementById('viewReservationsForm').submit();">View my reserved spots</a><br>
	<input name="action" value="getreservationsforview" type="hidden" style="width: 100px; margin-left: 30px;">
</form>
<form name="cancelreservationForm" id="cancelreservationForm" action="ReservedSpotsController" method ="get">
	<a href="#" onclick="document.getElementById('cancelreservationForm').submit();">Modify or Cancel my reservation</a><br>
	<input name="action" value="getreservationsforcancellation" type="hidden" style="width: 100px; margin-left: 30px;">
</form>
<a href="MyViolationsController">View my no-shows and violations</a><br>
<a href="ReservationStatusController"></a>
<a href="LogoutController">Logout</a><br>
</body>
</html>
