<%@ page import="model.User" %>
<%@ page import="model.CategoryElement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% User user = (User) request.getAttribute("user");%>
<div style="text-align: center;">
    <h1>Edit</h1>
    <form action="update" method="post">
        <table align="center">
            <tr>
                <td align="right">id:</td>
                <td align="left"><input value="<%=user.getId()%>" readonly type="number" name="id"></td>
            </tr>
            <tr>
                <td align="right">nationalcode:</td>
                <td align="left"><input type="number" value="<%=user.getNationalCode()%>" placeholder="national code"
                                        name="nationalcode"></td>
            </tr>
            <tr>
                <td align="right">username:</td>
                <td align="left"><input type="text" value="<%=user.getUsername()%>" placeholder="username"
                                        name="username"></td>
            </tr>
            <tr>
                <td align="right">password:</td>
                <td align="left"><input type="password" value="<%=user.getPassword()%>" placeholder="password"
                                        name="password"></td>
            </tr>
            <tr>
                <td align="right">email:</td>
                <td align="left"><input type="text" value="<%=user.getEmailAddress()%>" placeholder="email"
                                        name="email"></td>
            </tr>
            <tr>
                <select name="activeCheck">
                    <option value="active" <%if (user.isActive()) {%>
                            selected <%}%>>active
                    </option>
                    <option value="deActive" <%if(!user.isActive()){%>selected <%}%>>deActive</option>

                </select>
            </tr>
            <tr>
                <select name="enableCheck">
                    <option value="enable" <%if (user.isEnable()) {%>
                            selected <%}%>>enable
                    </option>
                    <option value="disable"<%if(!user.isEnable()){%>selected <%}%>>disable</option>
                </select>
            </tr>
            <%if (user.getManager() != null) {%>
            <tr>
                <td align="right">manager id:</td>
                <td align="left"><input type="number" value="<%=user.getManager().getId()%>" placeholder="manager id"
                                        name="managerId"></td>
            </tr>
            <%} else {%>
            <tr>
                <td align="right">manager id:</td>
                <td align="left"><input type="number" value=" " placeholder="manager id" name="managerId"></td>
            </tr>
            <%}%>
            <tr>
                <td align="left">
                    <%List<String> roleList = (List<String>) request.getAttribute("roles");%>
                    <select name="categoryElement" id="categoryElementList">
                        <%for (String role : roleList) {%>
                        <option value="<%=role.toString()%>" <%if (user.getRole().getName().equals(role)) {%>
                                selected <%}%>>
                            <%=role.toString()%>
                        </option>
                        <%}%>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <BUTTON type="submit">update</BUTTON>
                </td>
                </td>
            </tr>
        </table>

    </form>
</div>
</body>
</html>