<%-- 
    Document   : student
    Created on : Feb 6, 2021, 8:30:31 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
        <style><%@include file="/css/table.css"%></style>
    </head>
    <body>
        <h1>FPT University</h1>
        <font color="red">
            Welcome, ${sessionScope.FULLNAME}
        </font><br/>
        <a href="Logout">Logout</a>
        
        <a href="SearchHistory">View history</a>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Time(minute)</th>
                    <th>Number of question</th>
                    <th>Quiz</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.listsub}" var="ls" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${ls.subjectID}</td>
                        <td>${ls.subjectName}</td>
                        <td>${ls.timeRemain}</td>
                        <td>${ls.noOfQuestion}</td>
                        <td>
                            <form action="TakeQuiz" method="POST">
                                <input type="hidden" name="sID" value="${ls.subjectID}" />
                                <input type="hidden" name="time" value="${ls.timeRemain}" />
                                <input type="hidden" name="noOfQues" value="${ls.noOfQuestion}" />
                                <input type="submit" value="Take Quiz" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>
    </body>
</html>
