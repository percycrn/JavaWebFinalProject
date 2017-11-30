<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>输入客户信息</title></head>
<body>
<h4>输入客户信息</h4>
<form action="connDB2.do" method="post">
    <table>
        <tr>
            <td>客户名：</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="登陆"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
<%
    String message1;
    if (session.getAttribute("loginMessage") == null) {
        message1 = "";
    } else {
        message1 = String.valueOf(session.getAttribute("loginMessage"));
    }
%>
<%= message1%>
</body>
</html>
