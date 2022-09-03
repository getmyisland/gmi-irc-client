<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Hello</title>
</head>
<body>
<h2>Hi There!</h2>
<br>
<h3>
<%  
java.util.Date today = new java.util.Date();
out.println("Today's date is: " + today);
%>
</h3>
<form method="get" action="ServerSelectionServlet">
<input type="submit" value="button">
</form>
</body>
</html>