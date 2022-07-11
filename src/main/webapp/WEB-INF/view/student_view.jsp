<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<head>
<style>
	<%@include file="../css/Style.css" %>
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opsence Technologies</title>
<link rel = "icon" href = 
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBD3IZaetX7CSlJDQSCK2Fvnj-NKubcO7ppw&usqp=CAU" 
        type = "image/x-icon">
</head>
<body>
	<div class="body">
		<div class="title">
			<h1>Student View</h1>
		</div>

		<form>
			<a href="student?type=add" class="btn btn-outline-primary">Add
				Student </a>
			<table class="table table-success table-striped">
				<tr>
					<th>*</th>
					<th>Name</th>
					<th>Mobile</th>
					<th>Date</th>
					<th>Action</th>
				</tr>
				
				<c:set var="count1" value="0" scope="page" />
				<c:forEach var="stud" items="${list}" varStatus="count">						
						<%-- <c:forEach var="a" begin="1" end="1"> 	 --%>
						<c:set var = "datef" value = "${stud.dob}"/>
						<c:set var="count1" value="${count1+1}" scope="page"/>
						<c:set var="count" value="${count1}" scope="page"/>
						<tr><td>${count}</td>
							<td>${stud.name}</td>
							<td>${stud.mobile}</td>
							 <td> ${fn:substring(datef, 8, 10)}/${fn:substring(datef, 5, 7)}/${fn:substring(datef, 0, 4)}</td>
						<td colspan="2">
						<a href="studentdb?type=edit&id=${stud.id}" class="btn btn-outline-info">Update</a>
	                    <a href="studentdb?type=delete&id=${stud.id}" class="btn btn-outline-danger" onclick="return confirm('Are you sure want to delete this record?')">Delete</a></td>
						</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>
