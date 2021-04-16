<%-- 
    Document   : createQuestion
    Created on : Feb 10, 2021, 4:20:11 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
        <style><%@include file="/css/register.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <div class="register">
            <h1>Create Question form</h1>
            <form action="CreateQuestion" method="POST">
                <c:set value="${requestScope.CREAT_QUES_ERR}" var="err"/>
                <c:set value="${requestScope.CREATE_SUCCESS}" var="success"/>
                Question ID: <input type="text" name="txtQuesID" value="${param.txtQuesID}" required/><br/>
                <c:if test="${not empty err.quesIDLenghtErr}">
                    <font color="red">
                        ${err.quesIDLenghtErr}
                    </font><br/>
                </c:if>
                <c:if test="${not empty err.quesIDExist}">
                    <font color="red">
                        ${err.quesIDExist}
                    </font><br/>
                </c:if>
                Question Content: <input type="text" name="txtQuesContent" value="${param.txtQuesContent}" required/><br/>
                <c:if test="${not empty err.quesContwentLenghtErr}">
                    <font color="red">
                        ${err.quesContwentLenghtErr}
                    </font><br/>
                </c:if>
                Answer Content: <input type="text" name="txtAnsContent" value="${param.txtAnsContent}" required/><br/>
                
                Option A: <input type="text" name="txtAnsA" value="${param.txtAnsA}" required/><br/>
                <c:if test="${not empty err.ansALengthErr}">
                    <font color="red">
                        ${err.ansALengthErr}
                    </font><br/>
                </c:if>
                Option B: <input type="text" name="txtAnsB" value="${param.txtAnsB}" required/><br/>
                <c:if test="${not empty err.ansBLenghtErr}">
                    <font color="red">
                        ${err.ansBLenghtErr}
                    </font><br/>
                </c:if>
                Option C: <input type="text" name="txtAnsC" value="${param.txtAnsC}" required/><br/>
                <c:if test="${not empty err.ansCLenghtErr}">
                    <font color="red">
                        ${err.ansCLenghtErr}
                    </font><br/>
                </c:if>
                Option D: <input type="text" name="txtAnsD" value="${param.txtAnsD}" required/><br/>
                <c:if test="${not empty err.ansDLenghtErr}">
                    <font color="red">
                        ${err.ansDLenghtErr}
                    </font><br/>
                </c:if>
                Correct Answer: <input type="text" name="correctAns" value="${param.correctAns}" /><br/>
                <c:if test="${not empty err.ansCorrectNotMatch}">
                    <font color="red">
                        ${err.ansCorrectNotMatch}
                    </font><br/>
                </c:if>
                Subject ID: <select name="sid">
                                <c:forEach items="${sessionScope.listsub}" var="ls">
                                    <option>${ls.subjectID}</option>
                                </c:forEach>
                            </select><br/>
                Status: <select name="st">
                            <option>Active</option>
                            <option>Deactive</option>
                        </select><br/>
                
                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                <input type="hidden" name="cbStatus" value="${param.cbStatus}" />
                <input type="hidden" name="cbSubject" value="${param.cbSubject}" />
                <input type="hidden" name="index" value="${param.index}" />
                
                <input type="submit" value="Create" class="w3-button w3-blue" style="height:50px;width:100px;"/>
                <input type="reset" value="Reset" class="w3-button w3-blue" style="height:50px;width:100px;"/><br/>
                
                <c:url value="searchQuestion" var="searchURL">
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                    <c:param name="index" value="${param.index}"/>
                </c:url>
                <a href="${searchURL}">Back to search question</a><br/>
                <c:if test="${not empty success}">
                    <font color="green">
                        ${success}
                    </font><br/>
                </c:if>
            </form>
        </div>
        
    </body>
</html>
