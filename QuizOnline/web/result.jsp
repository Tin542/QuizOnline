<%-- 
    Document   : result
    Created on : Feb 16, 2021, 10:59:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
        <style><%@include file="/css/result.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <div class="title">
            <font color="white">
                Welcome, ${sessionScope.FULLNAME}
            </font><br/>
            <a href="Logout">Logout</a><br/>
            <h2>FPT University</h2>
        </div>
        <div class="kq">
            <h1>Result</h1>
            <h2>Name: ${sessionScope.FULLNAME}</h2>
            <h2>Correct answers: ${requestScope.noOfCorrect}</h2>
            <h2>Grade: ${requestScope.grade}</h2>
            <button class="w3-button w3-blue"><a href="student.jsp">Back to Course</a></button>
        </div>
    </body>
</html>
