<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App</title>
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
<h1 style="padding:12px">Welcome  <span style="color:SteelBlue">${user.username}</span></h1>
<div class="bodypart">
	<div class="bodypart1">
	<img src="<c:url value="/images/diary.jpg"/>">
	</div>
	<div class="bodypart2">
	<h1>UPDATE ENTRY</h1><br/><br/>
	<form action="./processentryupdate" method="POST">
		<table>
		<tr><td><label>Date </label></td> <td><input type="text" name="entrydate" class="formcontrol" value="<fmt:formatDate value="${entry.entrydate}" pattern="yyyy-MM-dd"/>" readonly></td></tr>
		<tr><td><label>Description </label></td>
		<td><textarea rows="10" cols="30" name="description">
		 ${entry.description}
		</textarea></td></tr>
		</table>
		<input type="hidden" name="userid" value="${user.id}">
		<input type="hidden" name="id" value="${entry.id}">
		
		<br/><br/></br>
		<div>
		<button type="submit">UPDATE ENTRY</button>
		<a href="./userhome"><button type="button">BACK TO HOME</button></a>
		
		</div>
		<br/><br/>
	
	</form>
	</div>

</div>


</body>
</html>