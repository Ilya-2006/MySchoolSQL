<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DzMessage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp"/>
        <center>
            <h3 style="color: blue;">Домашнее задание</h3>
            <h3 style="color: blue;">Урок:${send}</h3>
        <form action="/dzMessag" method="post">   
            <textarea rows="10" cols="100" name="send"></textarea>
            <input type="submit" value="Send" button type="button" class="btn btn-primary" >
        </form> 
        </center>
    </body>
</html> 
