<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<style>
		<%@include file="../css/Style.css" %>
	</style>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Opsence Technologies</title><link rel = "icon" href = 
"data:image/png;base64,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" 
        type = "image/x-icon">
    </head>
    <body>
    <div class="container-fluid">
    <div class="title">
        <h1>Update Student Information</h1>
        	</div>
        <form action="studentdb" method="POST" class="row g-3">
            <input type="hidden" name="type" value="EDITSAVE">
         <div class="col-7">
                <label for="inputId" class="form-label">Student Id  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${stud.id}</label>
                <input type="hidden" class="form-control" placeholder="Enter Your Name" name="id" value="${stud.id}">
             </div>
                <div class="col-7">
                <label for="inputName" class="form-label">Enter Your Name</label>
                <input type="text" class="form-control" placeholder="Enter Your Name" name="name" value="${stud.name}">
             </div>
              <div class="col-7">
                <label for="inputMobile" class="form-label">Enter Your Mobile Number</label>
                <input type="tel" class="form-control" placeholder="Enter Your Mobile Number" name="mobile" value="${stud.mobile}" minlength="10" maxlength="10" required
		      		pattern="[7-9]{1}[0-9]{9}" > 
             </div>
               <div class="col-7">
                <label for="inputDob" class="form-label">Select Date Of Birth</label>
                <input type="date" class="form-control" name="dob" value="${stud.dob}" pattern="DD-MM-YYYY">
             </div>
              <div>
                 <button type="submit" class="btn btn-primary" name="btnSubmit" value="Save">Submit</button>
               </div>
          
        </form>
        </div>
    </body>
</html>
