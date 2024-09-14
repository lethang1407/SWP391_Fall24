<%-- 
    Document   : list
    Created on : Sep 12, 2024, 12:48:33 PM
    Author     : KEISHA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Name</td>
                <td>Role</td>
                
            </tr>
            <c:forEach items="${requestScope.users}" var="a">
                <tr>
                    <td>${a.fullName}</td>
                    <td>${a.role}</td>
                    
                </tr>   
            </c:forEach>
        </table>
        
    </body>
</html>
