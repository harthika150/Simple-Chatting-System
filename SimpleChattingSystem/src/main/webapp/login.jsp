<!DOCTYPE html>
<html>
<head>
    <title>Chat Login</title>
</head>
<body>

<h2>Simple Chat Login</h2>

<form action="LoginServlet" method="post">

    <label>Username:</label>
    <input type="text" name="username" required>
    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>
    <br><br>

    <button type="submit">Login</button>

</form>

<%
String error = request.getParameter("error");
if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
}
%>

</body>
</html>