<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
    <center>
        <h3 style="color: blue;">Моя Анкета ${surname} ${firstname} ${lastname} </h3>
  
    <div class="container"> 
    <form action="/rnmProfile" enctype="multipart/form-data" method="post">  
        <h4 class="text-success">Пол:${sex}</h4>
        <h4 class="text-success">Дата рождения:<calendar:formatDate value="${datebirth}" pattern="dd-MM-yyyy"/></h4>
        <h4 class="text-success">Образование:${education}</h4>
        <h4 class="text-success">Должность:${positions}</h4>
        <h4 class="text-success">Адрес:</h4><textarea rows="1" cols="30" name=adres>${adres}</textarea>
        <h4 class="text-success">Телефон-1:</h4><textarea rows="1" cols="30" name=phone1>${phone1}</textarea>
        <h4 class="text-success">Телефон-2:</h4><textarea rows="1" cols="30" name=phone2>${phone2}</textarea>
        <h4 class="text-success">E-mail-1:</h4><textarea rows="1" cols="30" name=email1>${email1}</textarea>
        <h4 class="text-success">E-mail-2:</h4><textarea rows="1" cols="30" name=email2>${email2}</textarea>
        <h4 class="text-success">Ваше фото:</label></h4>
        <img src="data:image/jpg;base64,${photo}" width="200" height="200" alt="No image">
        <input type="file" name="photo" >          
        <input type="submit" value="Изменить" button type="button" class="btn btn-primary" >
    </form> 
        
    </div>
          </center>
   </body>
</html>          
