<%@ page import="model.Leave" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/28/2020
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LeaveList</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of leaves</h2></caption>
        <tr>
            <th>ID</th>
            <th>DATE</th>
            <th>FROM</th>
            <th>TO</th>
            <th>LEAVE ACTIVE</th>
            <th>LEAVE CREATION DATE</th>
            <th>LEAVE MODIFICATION DATE</th>
            <th>VERSION</th>
            <th>STATUS</th>
            <th>UPDATE/DELETE</th>
        </tr>
        <% ArrayList<Leave> leaveList =
                (ArrayList<Leave>) request.getAttribute("leaveList");
            for (Leave leave : leaveList) {%>
                <%if(leave.isEnable()){%>
        <tr>
            <td><%=leave.getId()%>
            </td>
            <td><%=leave.getDate()%>
            </td>
            <td><%=leave.getFromTime()%>
            </td>
            <td><%=leave.getToTime()%>
            </td>
            <td><%if(leave.isActive()){%>
                Active
                <%}else{%>
                DeActive
                <%}%>
            </td>
            <td><%=leave.getCreationDate()%>
            </td>
            <td><%=leave.getModificationDate()%>
            </td>
            <td><%=leave.getVersion()%>
            </td>
            <td><%=leave.getLeaveStatus().getName()%>
            </td>
            <td>
                <a href="edit?id=<%=leave.getId()%>">Edit</a>
                <a href="delete?id=<%=leave.getId()%>">Delete</a>
            </td>
        </tr>
        <%}%>
        <%}%>
        <td>
        </td>
        </tr>

    </table>
</div>
</body>
</html>
