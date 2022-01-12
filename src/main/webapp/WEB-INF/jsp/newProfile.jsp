<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp"/>
    <center>
        <form action="/addProfile" enctype="multipart/form-data" method="post">   
            Фамилия:<br/><input type="text" size ="10" name="surname" required><br/>
            Имя:<br/><input type="text" size="10" name="firstname" required><br/>
            Отчество:<br/><input type="text" size ="10" name="lastname" required><br/>
            Пол:<br/><select lass="mdb-select md-form" name="sex">
                <option selected value="Мужской">Мужской</option>
                <option selected value="Женский">Женский</option>
            </select><br/>
            Дата рождения:<br/><input type="date" name="datebirth" required><br/>
            Образование:<br/><select lass="mdb-select md-form" name="education">
                <option selected value="Ученик">Ученик</option>
                <option selected value="Среднее">Среднее</option>
                <option selected value="Среднее специальное">Среднее специальное</option>
                <option selected value="Высшее">Высшее</option>
            </select><br/>
            Телефон-1:<br/><input type="text" name="phone1" required ><br/>
            Телефон-2:<br/><input type="text" name="phone2" required ><br/>
            E-mail-1:<br/><input type="text" name="email1" required ><br/>
            E-mail-2:<br/><input type="text" name="email2" required ><br/>
            Адрес:<br/><input type="text" size="60" name="adres" required><br/>
            <p><label>Ваше фото</label></p>
            <input type="file" name="photo" ><br/>          
            <input type="submit" value="Сохранить" button type="button" class="btn btn-primary" >
        </form> 
     </center>
    </body>
</html> 