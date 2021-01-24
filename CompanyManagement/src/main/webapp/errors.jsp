<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<div style="text-align: center;">
    <% List<String> errors = (List<String>) request.getAttribute("errorMessages");%>
    <%for (String s : errors) {%>
    <a><%=s%>
    </a></
>
<br>
<%}%>
</center>
</body>
</html>
