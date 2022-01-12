<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Warinig</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

    <div class="container">
        <center>
        <h4 style="color: red;">Внимание! Вы действительно хотите удалить весь план занятй?</h4>
        <form action="/warning_NO" method="post"> 
            <input type="submit" value="NO" button type="button" class="btn btn-primary" >
        </form> 
        <form action="/warning_YES" method="post"> 
            <input type="submit" value="YES" button type="button" class="btn btn-danger" >
        </form> 
        </center>
    </div>
    <!-- /container -->
   
</body>
</html>

