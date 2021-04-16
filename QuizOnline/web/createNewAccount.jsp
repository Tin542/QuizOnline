<%-- 
    Document   : createNewAccount
    Created on : Feb 5, 2021, 1:19:23 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <style><%@include file="/css/register.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <div class="register">
            <h1>Register</h1>
            <form action="CreateNewAccount" method="POST">
                <c:set var="createErr" value="${requestScope.CREATE_ERR}"/>
                <c:set var="crerateSuccess" value="${requestScope.CREATE_SUCCESS}"/>

                <input type="text" name="txtEmail" value="${param.txtEmail}" placeholder="Email" /><br/>
                <c:if test="${not empty createErr.emailWrongFormatErr}">
                    <font color="red">${createErr.emailWrongFormatErr}</font><br/>
                </c:if>
                <c:if test="${not empty createErr.emailLengthErr}">
                    <font color="red">${createErr.emailLengthErr}</font><br/>
                </c:if>
                <c:if test="${not empty createErr.emailIsExisted}">
                    <font color="red">${createErr.emailIsExisted}</font><br/>
                </c:if>

                <input type="text" name="txtPassword" value="" placeholder="Password" /><br/>
                <c:if test="${not empty createErr.passwordLengthErr}">
                    <font color="red">${createErr.passwordLengthErr}</font><br/>
                </c:if>

                <input type="text" name="txtConfirm" value="" placeholder="Confirm password" /><br/>
                <c:if test="${not empty createErr.confirmNotMatched}">
                    <font color="red">${createErr.confirmNotMatched}</font><br/>
                </c:if>

                    <input type="text" name="txtName" value="${param.txtName}" placeholder="Full name" /><br/>
                <c:if test="${not empty createErr.fullNameLengthErr}">
                    <font color="red">${createErr.fullNameLengthErr}</font><br/>
                </c:if>
                    
                <c:if test="${not empty createErr.emptyInputErr}">
                    <font color="red">${createErr.emptyInputErr}</font><br/>
                </c:if>
                    <input type="submit" value="Register" class="w3-button w3-blue"/><br/>
                <c:if test="${not empty crerateSuccess}">
                    <font color="green">${crerateSuccess}</font><br/>
                </c:if>
                    
                <button class="w3-button w3-green"><a href="login.jsp">Back to login</a></button>
            </form>
        </div>
    </body>
</html>
