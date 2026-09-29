<!DOCTYPE html>
<html>
<head>
    <title>Simple Chat</title>
</head>
<body>

<h2>Simple Chatting System</h2>

<div id="messages">
    <iframe src="ChatServlet"
            width="500"
            height="300">
    </iframe>
</div>

<br>

<form action="ChatServlet" method="post">

    <input type="text"
           name="message"
           placeholder="Type your message"
           required>

    <button type="submit">Send</button>

</form>

<br>

<a href="login.jsp">Logout</a>

</body>
</html>