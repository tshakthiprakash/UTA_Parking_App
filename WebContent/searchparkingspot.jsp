<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Search Parking Spot</title>
</head>
<body>

<c:set var="userPermit" value="${user_info.permit_type}"></c:set>
<c:set var="reservationid" value="${reservationid}"></c:set>
<c:set var="userStatus" value="${user_info.user_status}"></c:set>
<table><tr>
<c:if test="${'Student/Faculty'==loggedinuserrole}">
<td class="tabcontent"><a href='stuFacHomePageController'>Home</a></td> 
</c:if>
<c:if test="${'Manager'==loggedinuserrole}">
<td class="tabcontent"><a href='managerHomePageController'>Home</a></td> 
</c:if>
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>Search Parking Spot</h1>
<c:if test = "${'Revoked'==userStatus}">
	<input id="alert" name="AlertMsg" value="<c:out value='You cannot make reservation because your account is revoked, please contact parking manager.'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:if>
<c:if test = "${3<=reservationsCount}">
	<input id="resErr" name="reservationErrorMsg" value="<c:out value='${reservationErrorMsgs.reservationErrormsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	<input id="resCntErr" name="reservationCountErrorMsg" value="<c:out value='${reservationErrorMsgs.reservedCountErrorMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:if>

<c:choose>
			<c:when test = '${reservationErrorMsgs.errormsg ne ""}'>
				<input id="errMsg" name="errMsg" value="<c:out value='${reservationErrorMsgs.errormsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
			</c:when>
			<c:otherwise>
				<input id="err" name="errMsg" value="<c:out value='${searchParkingErrorMsgs.errormsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
			</c:otherwise>
	</c:choose>	



<form name="reg_form" id="reg_form" action="parkingspotController" method ="get">
<input id="reservationid" name="reservationid" style="display:none" value="${reservationid}"/>
<input id="username" name="username" style="display:none" value="${username}"/>
<table>
<tr>
<td style="width: 160px;">Parking Area* :</td>
<td><select id="name" name="parkingarea">
<c:forEach items="${searchparkingnames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td>
<td> <input id="parkingErr" name="parkingAreaError" value="<c:out value='${searchParkingErrorMsgs.parkingareaNameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td style="width: 160px;">Parking Type* :</td>
<td><select id="type" name="parkingtype">
	<c:choose>
			<c:when test = "${'Premium' == userPermit}">
				<option value = "Select" ${parkingArea.parkingtype == 'Select' ? 'selected' : ''}>Select</option>
				<option value = "Basic" ${parkingArea.parkingtype == 'Basic' ? 'selected' : ''}>Basic</option>
				<option value = "Midrange" ${parkingArea.parkingtype == 'Midrange' ? 'selected' : ''}>Midrange</option>
				<option value = "Premium" ${parkingArea.parkingtype == 'Premium' ? 'selected' : ''}>Premium</option>
			</c:when>
			<c:when test = "${'Midrange' == userPermit}">
				<option value = "Select" ${parkingArea.parkingtype == 'Select' ? 'selected' : ''}>Select</option>
				<option value = "Basic" ${parkingArea.parkingtype == 'Basic' ? 'selected' : ''}>Basic</option>
				<option value = "Midrange" ${parkingArea.parkingtype == 'Midrange' ? 'selected' : ''}>Midrange</option>
			</c:when>
			<c:when test = "${'Basic' == userPermit}">
				<option value = "Select" ${parkingArea.parkingtype == 'Select' ? 'selected' : ''}>Select</option>
				<option value = "Basic" ${parkingArea.parkingtype == 'Basic' ? 'selected' : ''}>Basic</option>
			</c:when>
			<c:when test = "${'Access' == userPermit}">
				<option value = "Select" ${parkingArea.parkingtype == 'Select' ? 'selected' : ''}>Select</option>
				<option value = "Access" ${parkingArea.parkingtype == 'Access' ? 'selected' : ''}>Access</option>
			</c:when>
			<c:otherwise>
				<option value = "Select" ${parkingArea.parkingtype == 'Select' ? 'selected' : ''}>Select</option>
				<option value = "Basic" ${parkingArea.parkingtype == 'Basic' ? 'selected' : ''}>Basic</option>
			</c:otherwise>
	</c:choose>		
</select></td>


<td> <input id="typeErr" name="parkingTypeError" value="<c:out value='${searchParkingErrorMsgs.parkingTypeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<tr>
<td style="width: 160px;">From(24-hour format)* :</td><td><input type="text" name ="reservationfrom" id ="reservationfrom" onblur="myFunction()" value="<c:out value='${reservationfromtime}'/>" ></td>
<td> <input id="fromErr" name="reservationFromError" value="<c:out value='${reservationErrorMsgs.reservationFromError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td style="width: 160px;">To(24-hour format)* :</td><td><input type="text" name ="reservationto" id ="reservationto" onblur="myFunction()" value="<c:out value='${reservationtotime}'/>" ></td>
<td> <input id="toErr" name="reservationToError" value="<c:out value='${reservationErrorMsgs.reservationToError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>

</table>

<br/>
		Select options:
		<br/>
 		<input type="checkbox" id="selectedcart" name="selectedcart" style="margin-left: 30px; margin-top: 8px;"  ${selectedcart} onclick="myFunction()" >Cart
 		<input type="checkbox" id="selectedcamera" name="selectedcamera" style="margin-left: 30px; margin-top: 8px; " ${selectedcamera} onclick="myFunction()">Camera
 		<input type="checkbox" id="selectedhistory" name="selectedhistory" style="margin-left: 30px; margin-top: 8px; " ${selectedhistory} onclick="myFunction()">History
		<br/>
		<p id="cart" style="display:none">Cart: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $15.95</p>
		<p id="camera" style="display:none">Camera: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $2.95</p>
		<p id="history" style="display:none">History: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $1.95</p>
		<p id="baseprice" style="display:none"></p>
		<p id="tax" style="display:none"></p>
		
		<p id="totalPrice" style="display:none;">Total Price: &nbsp;&nbsp; $0</p>
		
		<c:choose>
		<c:when test="${not empty totalcost}">
		   <input id="totalcost" name="totalcost" type="text" style="display:none;" value="<c:out value='${totalcost}'/>" />
		</c:when>
		<c:otherwise>
		    <input id="totalcost" name="totalcost" type="text" style="display:none;" value="<c:out value='0.00'/>" />
		</c:otherwise>
		</c:choose>

		
<br/>
	<input name="action" value="searchparkingspot" type="hidden" style="width: 100px; margin-left: 30px;">
	<input id="search" name="searchparkingspot" type="submit" value="Search" style="width: 100px; margin-left: 30px;" >
	<input id="reset" type="reset" value="Reset" style="width: 100px; margin-left: 30px;">
</form>
<br/>

<c:if test = "${!empty parkingspots}">
	
<form action="reserveparkingspot.jsp" name="table_form" id="table_form">
<input id="reservationid" name="reservationid" style="display:none" value="${reservationid}"/>
<input id="username" name="username" style="display:none" value="${username}"/>
<table class="myTable" id="t01"> 
		<tr class="myTableRow" align="CENTER">		
				
				<th class="myTableHead" style="width: 185px; ">Parking Area Name</th>
				<th class="myTableHead" style="width: 124px; ">Parking Type</th> 
				<th class="myTableHead" style="width: 105px; ">Floor</th>
				<th class="myTableHead" style="width: 160px; ">Available Spots </th>
				<th class="myTableHead" style="width: 160px; ">Price </th>
				<th class="myTableHead" style="width: 185px; " >Select</th>
		</tr>
		
 		<c:forEach items="${parkingspots}" var="item" varStatus="status">
 		
			<tr class="myTableRow">
			
			<c:set var="keyString" value="${item.parkingarea_id}"></c:set>
			<c:set var="count" value="${availabilitymap[keyString]}"></c:set>			
			<td id="name${status.count}"class="myTableCell" style="width: 145px;" align=CENTER >
					<c:out value="${item.parkingarea_name}" />
				</td>
			
			<td class="myTableCell" id="type${status.count}" style="width: 104px; " align=CENTER ><c:out value="${item.parkingtype}" /></td>
			<td class="myTableCell" id="floor${status.count}" style="width: 130px; " align=CENTER ><c:out value="${item.floor}" /></td>
			<td class="myTableCell" id="count${status.count}" style="width: 63px; " align=CENTER ><c:out value="${count}" /></td>
			<td class="myTableCell" id="totalcost${status.count}" style="width: 63px; " align=CENTER ><c:out value="${totalcost}" /></td>
			<c:choose>
			<c:when test = "${count ne 0 and 'Active'==userStatus and reservationsCount<3}">
				<td class="myTableCell" style="width: 145px;" align=CENTER >
					<input type="radio" id="radioButton${status.count}" name="radioButton" value="${status.count}" onclick="check();">
				</td>
			</c:when>
			<c:otherwise>			
				<td class="myTableCell" style="width: 145px;" align=CENTER >
					<input type="radio" name="radioButton" style="display:none" value="${status.count}"  >
				</td>
			</c:otherwise>
			</c:choose>

			</tr>
		</c:forEach>
	
</table>
	<br/>
	<input type="submit" name="reserveButton" id="reserveButton" value="Proceed to reserve" style="margin-left: 285px;" disabled="disabled" ></input>	
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
 	document.getElementById('reserveButton').disabled=false;
 }
 }
 
	function myFunction() {
	  var cartCheckBox = document.getElementById("selectedcart");
	  var cameraCheckBox = document.getElementById("selectedcamera");
	  var historyCheckBox = document.getElementById("selectedhistory");
	  var cart = document.getElementById("cart");
	  var camera = document.getElementById("camera");
	  var history = document.getElementById("history");
	  var tax = document.getElementById("tax");
	  var baseprice = document.getElementById("baseprice");
	  var totalprice = document.getElementById("totalPrice");
	  
	  var fromtime = document.getElementById("reservationfrom").value;
	  var totime = document.getElementById("reservationto").value;
	  
	  var fromtimeSplit = fromtime.split(":");
	  var totimeSplit = totime.split(":");
	  
	  var taxValueCart = 0;
	  var taxValueCamera = 0;
	  var taxValueHistory = 0;
	  var taxValue = 0;
	  
	  var cartValue = 0;
	  var cameraValue = 0;
	  var historyValue = 0;
	  var totalTaxValue = 0;
	  
	  var d = new Date();
	  var todayDay = d.getDay();
	  
	  if (cartCheckBox.checked == true){
	      //cart.style.display = "block";
	      if(todayDay==1 && (parseInt(fromtimeSplit[0])<12 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
	    	  cartValue = 31.90
	    	  taxValueCart = 2.60;
	      } else if(todayDay==7 && (parseInt(fromtimeSplit[0])<8 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
	    	  cartValue = 31.90
	    	  taxValueCart = 2.60;
	      } else if(parseInt(fromtimeSplit[0])<6 || parseInt(fromtimeSplit[0])>19 || parseInt(totimeSplit[0])>20) {
	    	  cartValue = 31.90
	    	  taxValueCart = 2.60;
		  } else {
	      	cartValue=15.95;
	      	taxValueCart = 1.30;
	      }
	  } else {
		  cart.style.display = "none";
	  }
	  
	  if (cameraCheckBox.checked == true){
		  //camera.style.display = "block";
		  if(todayDay==1 && (parseInt(fromtimeSplit[0])<12 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
			  cameraValue = 5.90
			  taxValueCamera = 0.50;
	      } else if(todayDay==7 && (parseInt(fromtimeSplit[0])<8 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
	    	  cameraValue = 5.90
	    	  taxValueCamera = 0.50;
	      } else if(parseInt(fromtimeSplit[0])<6 || parseInt(fromtimeSplit[0])>19 || parseInt(totimeSplit[0])>20) {
	    	  cameraValue = 5.90
	    	  taxValueCamera = 0.50;
	      } else {
			  cameraValue = 2.95;
			  taxValueCamera = 0.25;
	      }
	  } else {
		  camera.style.display = "none";
	  }
	  
	  if (historyCheckBox.checked == true){
		  //history.style.display = "block";
		  if(todayDay==1 && (parseInt(fromtimeSplit[0])<12 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
			  historyValue = 3.90
			  taxValueHistory = 0.30;
	      } else if(todayDay==7 && (parseInt(fromtimeSplit[0])<8 || parseInt(fromtimeSplit[0])>16 || parseInt(totimeSplit[0])>17)) {
	    	  historyValue = 3.90
	    	  taxValueHistory = 0.30;
	      } else if(parseInt(fromtimeSplit[0])<6 || parseInt(fromtimeSplit[0])>19 || parseInt(totimeSplit[0])>20) {
	    	  historyValue = 3.90
	    	  taxValueHistory = 0.30;
	      } else {
			  historyValue = 1.95;
			  taxValueHistory = 0.15;
	      }
	  } else {
		  history.style.display = "none";
	  }
	  if(cartCheckBox.checked == true || cameraCheckBox.checked == true || historyCheckBox.checked == true) {
		  //tax.style.display = "block";
		  //baseprice.style.display = "block";
		  var k = taxValueCart+taxValueCamera+taxValueHistory;
		  var m = cartValue+cameraValue+historyValue;
		  if(cartCheckBox.checked == true)
		  	  k = k.toPrecision(3);
		  else
			  k = k.toPrecision(2);
		  tax.innerHTML = "Tax(8.25%): &nbsp;&nbsp; $" +k;
		  baseprice.innerHTML = "Base price:&nbsp;&nbsp; $"+m;
		 // document.getElementById("makepayment").style.display = "block";
		 // document.getElementById("confirmreservation").style.display = "none";
		}
	  else{
		  tax.style.display="none";
	  }
	  var p = taxValueCart+taxValueCamera+taxValueHistory+historyValue+cameraValue+cartValue;
	  if(cartCheckBox.checked == true)
	  	  p = p.toPrecision(4);
	  else
		  p = p.toPrecision(3);
	  totalprice.innerHTML = "Total Price: &nbsp;&nbsp;&nbsp; $"+p;
	  document.getElementById("totalcost").value = p;
	  session.setAttribute("totalcost", p);
	}
     
 </script>
</body>
</html>
