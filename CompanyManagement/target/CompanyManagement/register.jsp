<%@ page import="java.util.ArrayList" %>
<%@ page import="model.CategoryElement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div style="text-align: center;">
    <h1>Register</h1>
    <form action="user/add" method="post">
        <table align="center">
            <tr>
                <td align="right">nationalcode:</td>
                <td align="left"> <input type="number" placeholder="national code" name="nationalcode"></td>
            </tr>
            <tr>
                <td align="right">username:</td>
                <td align="left"><input type="text" placeholder="username" name="username"></td>
            </tr>
            <tr>
                <td align="right">password:</td>
                <td align="left"><input type="password" placeholder="password" name="password"></td>
            </tr>
            <tr>
                <td align="right">email:</td>
                <td align="left"><input type="text" placeholder="email" name="email"></td>
            </tr>
            <tr>
                <td align="right">manager id:</td>
                <td align="left"><input type="number" placeholder="manager id" name="managerId"></td>
            </tr>
            <tr>
                <td align="left">
                <%List<String> roleList= (List<String>) request.getAttribute("roles");%>
                    <select name="categoryElement" id="categoryElementList">
                        <%for(String role:roleList){%>
                            <option  value="<%=role.toString()%>">
                                <%=role.toString()%>
                            </option>
                       <%}%>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center"><BUTTON type="submit">register</BUTTON></td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>