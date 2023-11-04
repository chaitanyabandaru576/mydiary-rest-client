<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary view entry page</title>
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
	<h1>VIEW ENTRY</h1><br/><br/>
	<TABLE>
	<tr><td>Date:</td><td><fmt:formatDate value="${entry.entrydate}" pattern="EEE MMM yyyy"/></td></tr>
	<tr><td>Description</td><td>${entry.description}</td></tr>
	</TABLE>
	<br/><br/></br>
		<a href="./userhome"><button type="button">BACK TO HOME</button></a>
	</div>

</div>


</body>
</html>