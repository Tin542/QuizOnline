<%-- 
    Document   : editQuestion
    Created on : Feb 10, 2021, 2:13:54 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Question</title>
        <style><%@include file="/css/register.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> 
    </head>
    <body>
        <div class="register">
            <h1>Edit form</h1>
            <form action="EditQuestion" method="POST">
                Question content: <input type="text" name="txtQuestionContent" value="${requestScope.quest_Content}" required/><br/>
                Answer content: <input type="text" name="txtAnswerContent" value="${requestScope.ansContent}" required /><br/>
                Correct answer: <input type="text" name="corectAns" value="${requestScope.quest_Correct}" required/><br/>
                Subject ID: <select name="sID">
                                <c:forEach items="${sessionScope.listsub}" var="ls">
                                    <option value="${ls.subjectID}" ${requestScope.subID == ls.subjectID?'selected':''}>${ls.subjectID}</option>
                                </c:forEach>
                            </select><br/>
                Status: <select name="SelectStatus">
                            <option ${requestScope.SelectStatus == 'Active'?'selected':''}>Active</option>
                            <option ${requestScope.SelectStatus == 'Deactive'?'selected':''}>Deactive</option>
                        </select><br/>
                
                Answer options:<br/>        
                <c:forEach items="${requestScope.lisAns}" var="la" varStatus="counter">
                    <input type="text" name="txtAnsOption${counter.count}" value="${la.answer_content}" /><br/>
                </c:forEach>
                    
                <input type="hidden" name="searchValue" value="${requestScope.txtSearchValue}" />
                <input type="hidden" name="searchSubID" value="${requestScope.cbSubject}" />
                <input type="hidden" name="searchStatus" value="${requestScope.cbStatus}" />
                <input type="hidden" name="index" value="${requestScope.index}" />
                
                <input type="hidden" name="txtQID" value="${param.quesID}" />
                
                <input type="submit" value="Edit" class="w3-button w3-blue"/><br/>
                
                <c:url value="SearchQuestion" var="editURL">
                    <c:param name="searchValue" value="${requestScope.txtSearchValue}"/>
                    <c:param name="searchStatus" value="${requestScope.cbStatus}"/>
                    <c:param name="searchSubID" value="${requestScope.cbSubject}"/>
                    <c:param name="index" value="${requestScope.index}"/>
                </c:url>
                <button class="w3-button w3-green"><a href="${editURL}">Back to search question</a><br/></button>
                <c:if test="${not empty requestScope.SUCCESS}">
                    <font clolor="green">
                        ${requestScope.SUCCESS}
                    <font/>
                </c:if>
            </form>
        </div>
        
    </body>
</html>
