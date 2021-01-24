<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>NATIONAL CODE</th>
            <th>USERNAME</th>
            <th>PASSWORD</th>
            <th>EMAIL</th>
            <th>VERSION</th>
            <th>CREATION DATE</th>
            <th>MODIFICATION DATE</th>
            <th>MANAGER</th>
            <th>ROLE</th>
            <th>ACTIVE</th>
            <th>UPDATE/DELETE</th>
        </tr>
        <% ArrayList<User> userList =
                (ArrayList<User>) request.getAttribute("userList");
            for (User user : userList) {%>
        <%if(user.isEnable()){%>
        <tr>

            <td><%=user.getId()%>
            </td>
            <td><%=user.getNationalCode()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getPassword()%>
            </td>
            <td><%=user.getEmailAddress()%>
            </td>
            <td><%=user.getVersion()%>
            </td>
            <td><%=user.getCreationDate()%>
            </td>
            <td><%=user.getModificationDate()%>
            </td>
            <%if (user.getManager() != null) {%>
            <td><%=user.getManager().getId()%>
            </td>
            <%} else {%>
            <td>GENERAL MANAGER
            </td>
            <%}%>
            <td><%=user.getRole().getName()%>
            </td>
            <%if (user.isActive()) {%>
            <td>Active
            </td>
            <%} else {%>
            <td>DeActive
            </td>
            <%}%>
            <td>
                <a href="edit?id=<%=user.getId()%>">Edit</a>
                <a href="delete?id=<%=user.getId()%>">Delete</a>
            </td>
        </tr>
        <td>
        </td>
        <%}%>
        <%}%>
        </tr>

    </table>
</div>
</body>
</html>
