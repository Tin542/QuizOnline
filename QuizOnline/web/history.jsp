<%-- 
    Document   : history
    Created on : Feb 17, 2021, 1:04:44 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
        <style><%@include file="/css/table.css"%></style>
    </head>
    <body>
        <div class="title">
            <font color="white">
                Welcome, ${sessionScope.FULLNAME}
            </font><br/>
            <a href="Logout">Logout</a><br/>
            <h2>FPT University</h2>
        </div>
        <form action="SearchHistory">
            <h1>Search for result</h1>
             Subject:   <select name="cbSubName">
                            <option></option>
                            <c:forEach items="${sessionScope.listsub}" var="ls">
                                <option>${ls.subjectName}</option>
                            </c:forEach>
                        </select><br/>
            <input type="submit" value="Search" /><br/>
            <c:if test="${not empty sessionScope.ADMIN}">
                <c:url value="SearchQuestion" var="searchURL">
                    <c:param name="txtSearchValue" value="${requestScope.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${requestScope.cbStatus}"/>
                    <c:param name="cbSubject" value="${requestScope.cbSubject}"/>
                    <c:param name="index" value="${requestScope.index}"/>
                </c:url>
                <a href="${searchURL}">Back to search question</a>
            </c:if>
            <c:if test="${not empty sessionScope.STUDENT}">
                <a href="student.jsp">Back to Home</a>
            </c:if>
        </form>

        <c:set value="${requestScope.listResult}" var="listR"/>
        <c:if test="${not empty listR}">
            <table border="1" style="width:1350px;height:300px">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Student's name</th>
                        <th>Subject</th>
                        <th>Correct Answer</th>
                        <th>Grade</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listR}" var="lr" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${lr.resultID}</td>
                            <td>${lr.email}</td>
                            <td>${lr.name}</td>
                            <td>${lr.subjectID}</td>
                            <td>${lr.noOfCorrect}</td>
                            <td>${lr.grade}</td>
                            <td>${lr.dateOfCreate}</td>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>

        </c:if>
        <c:forEach begin="1" end="${requestScope.p}" var="i">
            <c:url value="SearchHistory" var="paging">
                <c:param name="index" value="${i}"/>
                <c:param name="txtName" value="${param.txtName}"/>
                <c:param name="cbSubName" value="${param.cbSubName}"/>
            </c:url>
            <a href="${paging}">${i}</a>
        </c:forEach>
    </body>
</html>
