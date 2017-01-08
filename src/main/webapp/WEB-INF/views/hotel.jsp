<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<% String savedMessage="";
	if(request.getParameterMap().containsKey("isHotel1Saved"))
	{
		savedMessage=request.getParameter("isHotel1Saved");
	}
	String matchScore="";
	if(request.getParameterMap().containsKey("matchScore"))
	{
		matchScore=request.getParameter("matchScore")+"</br>Start by entering the details of first hotel";
	}

	boolean isStart=false;
	if(savedMessage.equals("") && matchScore.equals(""))
	{
		isStart=true;
	}
 %>
<html>
<head><title>Add Hotel</title></head>
<style>
div.ex {
	text-align: right width:300px;
	padding: 10px;
	border: 5px solid grey;
	margin: 0px
}
</style>
<body>

<div><%=savedMessage%></div>


<div><%=matchScore%></div>

<% if(isStart){%>
<div>Start by entering the details of first hotel.</div>
<%}%>


<div class="ex">
  <form:form action="/ixigoAssignment-1T/addHotel" method="POST">
  <table style="with: 50%">
    <tr>
  	<td>Hotel Name</td>
  	<td><input type="text" name="address.hotelName" /></td>
	</tr>
	
    <tr>
  	<td>Street</td>
  	<td><input type="text" name="address.street" /></td>
	</tr>
	<tr>
  	<td>Locality</td>
  	<td><input type="text" name="address.locality" /></td>
	</tr>
    <tr>
  	<td>City</td>
  	<td><input type="text" name="address.city" /></td>
	</tr>
   <tr>
  	<td>State</td>
  	<td><input type="text" name="address.state" /></td>
	</tr>
		<tr>
  	<td>Country</td>
  	<td><input type="text" name="address.country" /></td>
	</tr>
		<tr>
  	<td>Pincode</td>
  	<td><input type="text" name="address.pinCode" /></td>
	</tr>
	<tr>			
	<td>Landmark</td>
  	<td><input type="text" name="address.landmark" /></td>
	</tr>
	<tr>			
	<td>Latitude</td>
  	<td><input type="text" name="address.latitude" /></td>
	</tr>
	<tr>			
	<td>Longitude</td>
  	<td><input type="text" name="address.longitude" /></td>
	</tr>
	<tr>			
	<td>Phone Number</td>
  	<td><input type="text" name="phoneNumber" /></td>
	</tr>
	<tr>			
	<td>Number of rooms</td>
  	<td><input type="text" name="numberOfRooms" /></td>
	</tr>
	<tr>			
	<td>Number of floors</td>
  	<td><input type="text" name="numberOfFloors" /></td>
	</tr>
	<tr>			
	<td>Currency Type</td>
  	<td><input type="text" name="currencyType" /></td>
	</tr>
	<tr>			
	<td>Amenities</td>
  	<td><input type="text" name="amenities" /></td>
	</tr>
	<tr>			
	<td>Rating</td>
  	<td><input type="text" name="rating" /></td>
	</tr>
	<tr>			
	<td>Owner Name</td>
  	<td><input type="text" name="ownerName" /></td>
	</tr>
	<tr>			
	<td>Caretaker Name</td>
  	<td><input type="text" name="careTakerName" /></td>
	</tr>
	<tr>			
	<td>Price Range</td>
  	<td><input type="text" name="priceRange" /></td>
	</tr>
	<tr>			
	<td>Distance from airport</td>
  	<td><input type="text" name="distanceFromAirport" /></td>
	</tr>
	<tr>			
	<td>Distance from station</td>
  	<td><input type="text" name="distanceFromStation" /></td>
	</tr>
	<tr>			
	<td>VAT/TIN/CST registration number</td>
  	<td><input type="text" name="vATorTINorCST" /></td>
	</tr>
	<tr>			
	<td>Lease Agreement Number</td>
  	<td><input type="text" name="leaseAgreementNumber" /></td>
	</tr>
	<tr>			
	<td>Nearby Attraction</td>
  	<td><input type="text" name="nearByAttraction" /></td>
	</tr>
	</table>
   
   <input type="submit">  

  </form:form>
  </div>
</body>
</html>
 