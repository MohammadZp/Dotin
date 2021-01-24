<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload to Database Demo</title>
</head>
<body>
<div style="text-align: center;">
    <h1>File Upload to Database Demo</h1>
    <form method="post" action="email/add" enctype="multipart/form-data">
        <table border="0">
<%--            <tr>--%>
<%--                <td>Subject: </td>--%>
<%--                <td><input type="text" name="subject" size="50"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Text: </td>--%>
<%--                <td><input type="text" style="height:400px; width:900px;" name="text" /></td>--%>
<%--            </tr>--%>
            <tr>
                <td>attachments: </td>
                <td><input type="file"  name="attachments" size="50"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Send">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>