<%-- 
    Document   : createSubject
    Created on : Feb 18, 2021, 2:35:00 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Subject</title>
        <style><%@include file="/css/register.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <div class="register">
            <h1>Create form</h1>
            <c:set value="${requestScope.CREATE_SUB_ERR}" var="err"/>
            <c:set value="${requestScope.SUCCESS}" var="success"/>
            <form action="CreateSubject" method="POST">
                Subject ID: <input type="text" name="txtsubID" value="${param.txtsubID}" required/><br/>
                <c:if test="${not empty err.subIdlengthErr}">
                    <font color="red">
                        ${err.subIdlengthErr}<br/>
                    <font/>
                </c:if>
                <c:if test="${not empty err.subIDExist}">
                    <font color="red">
                        ${err.subIDExist}<br/>
                    <font/>
                </c:if>
                Subject Name: <input type="text" name="txtName" value="${param.txtName}" required/><br/>
                <c:if test="${not empty err.subNameLengthErr}">
                    <font color="red">
                        ${err.subNameLengthErr}<br/>
                    <font/>
                </c:if>
                Time: <input type="text" name="txtTime" value="${param.txtTime}" required/><br/>
                number of question: <input type="text" name="txtNo" value="${param.txtNo}" required/><br/>
                
                <input type="submit" value="Create" class="w3-button w3-blue" style="height:50px;width:100px;"/>
                <input type="reset" value="Reset" class="w3-button w3-blue" style="height:50px;width:100px;"/><br/>
                <c:if test="${not empty success}">
                    <font color="green">
                        ${success}
                    <font/>
                </c:if>
                 <c:url value="SearchQuestion" var="searchURL">
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                    <c:param name="index" value="${param.index}"/>
                </c:url>
                <button class="w3-button w3-green"><a href=${searchURL}>Back to search question</a><br/></button>
            </form>
        </div>
        
    </body>
</html>
