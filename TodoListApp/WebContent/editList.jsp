<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>To-do List Application</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
function validateForm() {
    var a = document.forms["addList"]["description"].value;
    var b = document.forms["addList"]["dueDate"].value;
    var c = document.forms["addList"]["status"].value;
    var d = document.forms["addList"]["dateCompleted"].value;
    var e = document.forms["addList"]["priority"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || e == null || e == "") {
        alert("All fields must be filled out.");
        return false;
    }
    
    if (c == "1") {
    	if (d == "" || d == null) {
    		alert("Please enter the completion date.")
    		return false;
    	}
    }
}
</script>
</head>
<body>

<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="todoListServlet">To-do List Application</a>
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<li><a href="viewCompletedTaskServlet">View Completed Tasks</a>
<li><a href="loginServlet?logout=true">Logout</a></li>
</ul>
</div>
</div>
</nav>
<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
<div class=container>
<form class="form-horizontal" role="form" name="editList" id="editList" onsubmit="return validateForm()" action="editListServlet" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="description">Description:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="description" id="description" value="${list.description}">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="dueDate">Due Date (MM-DD-YYYY):</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="dueDate" id="dueDate" value="${dueDate}">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="status">Status:</label>
<div class="col-sm-10">
<select class="form-control" name="status" id="status" onchange="selectCheck(this);">
<option value="3">Not Started Yet</option>
<option value="2">In Progress</option>
<option id="completedCheck" value="1">Completed</option>
</select>
</div>
</div>
<script language="javascript">
function selectCheck(nameSelect) {
    console.log(nameSelect);
    if(nameSelect){
        optionValue = document.getElementById("completedCheck").value;
        if(optionValue == nameSelect.value){
            document.getElementById("completed").style.display = "block";
        } else {
            document.getElementById("completed").style.display = "none";
        }
    } else {
        document.getElementById("completed").style.display = "none";
    }
}
</script>
<div id="completed" class="form-group" style="display:none;">
<label class="control-label col-sm-2" for="completionDate">Completion Date (MM-DD-YYYY):</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="completionDate" id="completionDate" value="${completionDate}">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="priority">Priority:</label>
<div class="col-sm-10">
<select class="form-control" name="priority" id="priority">
<option>High</option>
<option>Medium</option>
<option>Low</option>
</select>
</div>
</div>
<div class="form-group"> 
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="editListSub" id="editListSub">Submit</button>
</div>
</div>
</form>
</div>
${editListMsg}

</body>
</html>