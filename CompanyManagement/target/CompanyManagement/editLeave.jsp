<%@ page import="model.Leave" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/28/2020
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditLeave</title>
</head>
<body>
<% Leave leave = (Leave) request.getAttribute("leave");%>
<div style="text-align: center;">
    <h1>Leave request</h1>
    <form action="update" method="post">
        <table align="center">
<%--            <tr>--%>
<%--                <td align="right">User Id:</td>--%>
<%--                <td align="left"><input readonly value="<%=leave.getUser().getId()%>" type="number" placeholder="userId" name="userId"></td>--%>
<%--            </tr>--%>
            <tr>
                <td align="right">leave Id:</td>
                <td align="left"><input readonly value="<%=leave.getId()%>" type="number" placeholder="leaveId" name="leaveId"></td>
            </tr>
            <tr>
                <td align="right">date:</td>
                <td align="left"><input type="date" value="<%=leave.getDate()%>" placeholder="date" name="date"></td>
            </tr>
            <tr>
                <select name="activeCheck" >
                    <option value="active" <%if(leave.isActive()){%>selected<%}%>>active</option>
                    <option value="deActive" <%if(!leave.isActive()){%>selected<%}%>>deActive</option>

                </select>
            </tr>
            <tr>
                <select name="enableCheck" >
                    <option value="enable" <%if(leave.isEnable()){%>selected<%}%>>enable</option>
                    <option value="deActive" <%if(!leave.isEnable()){%>selected<%}%>>disable</option>
                </select>
            </tr>
            <tr>
                <td align="right">start Time:</td>
                <td align="left"><input type="time" value="<%=leave.getFromTime()%>" placeholder="start Time" name="fromTime"></td>
            </tr>
            <tr>
                <td align="right">finish Time:</td>
                <td align="left"><input type="time" value="<%=leave.getToTime()%>" placeholder="finish Time" name="toTime"></td>
            </tr>
            <tr>
                <td align="right">full day(8 hours)</td>
                <td align="left"><input type="checkbox" name="checkbox"></td>
            </tr>
            <tr>
                <td align="right"><BUTTON type="submit">update leave request</BUTTON></td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
