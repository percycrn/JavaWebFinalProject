<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<jsp:useBean id="studentTuple" class="com.StudentTuple" scope="session">
    <jsp:setProperty name="studentTuple" property="*"/>
</jsp:useBean>
<html>
<head><title>显示客户信息</title></head>
<body>
<h4>客户信息如下</h4>
<table border="1">
    <tr>
        <td>name:</td>
        <td>
            <jsp:getProperty name="studentTuple" property="name"/>
        </td>
    </tr>
    <tr>
        <td>password:</td>
        <td>
            <jsp:getProperty name="studentTuple" property="password"/>
        </td>
    </tr>
    <tr>
        <td>id:</td>
        <td>
            <jsp:getProperty name="studentTuple" property="id"/>
        </td>
    </tr>
    <tr>
        <td>address:</td>
        <td>
            <jsp:getProperty name="studentTuple" property="address"/>
        </td>
    </tr>
</table>
</body>
</html>
