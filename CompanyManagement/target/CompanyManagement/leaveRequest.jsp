<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Request</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Leave request</h1>
    <form action="leave/add" method="post">
        <table align="center">
<%--            <tr>--%>
<%--                <td align="right">user Id:</td>--%>
<%--                <td align="left"><input type="number" placeholder="userId" name="userId"></td>--%>
<%--            </tr>--%>
            <tr>
                <td align="right">date:</td>
                <td align="left"><input type="date" placeholder="date" name="date"></td>
            </tr>
            <tr>
                <td align="right">start Time:</td>
                <td align="left"><input type="time" placeholder="start Time" name="fromTime"></td>
            </tr>
            <tr>
                <td align="right">finish Time:</td>
                <td align="left"><input type="time" placeholder="finish Time" name="toTime"></td>
            </tr>
            <tr>
                <td align="right">full day(8 hours)</td>
                <td align="left"><input type="checkbox" name="checkbox"></td>
            </tr>
            <tr>
                <td align="right"><BUTTON type="submit">register leave request</BUTTON></td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
