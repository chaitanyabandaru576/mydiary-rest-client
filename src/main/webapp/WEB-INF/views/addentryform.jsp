<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App Homepage</title>
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<div class="header">
	<div class="first">
		<img src="<c:url value="/images/diary.jpg"/>" width="60" height="60">
		
	</div>
	<div class="second">MyDiary App</div>

</div>

<br/><br/>
<hr/>
<br/><br/>
<div class="userhome">
Welcome  <span style="color:SteelBlue">${user.username}</span>

<a href="./signout" style="color:red;float:right">Signout</a>
</div>
<div class="bodypart">
	<div class="bodypart1">
	<img src="<c:url value="/images/diary.jpg"/>">
	</div>
	<div class="bodypart2">
	<h1>ADD ENTRY</h1><br/><br/>
	<form action="./saveentry" method="POST">
		<table>
		<tr><td><label>Date </label></td><td> <input type="date" name="entrydate" class="formcontrol" required><br/><br/></td></tr>
		<tr><td><label>Description </label></td>
		<td><textarea rows="10" cols="30" name="description">
		</textarea></td></tr>
		</table>
		<input type="hidden" name="userid" value="${user.id}">
		
		<br/><br/></br>
		<div>
		<button type="submit">SAVE ENTRY</button>
		<a href="./userhome"><button type="button">BACK TO HOME</button></a>
		
		</div>
		
	
	</form>
	</div>

</div>


</body>
</html>