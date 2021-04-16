<%-- 
    Document   : admin
    Created on : Feb 6, 2021, 8:30:21 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <style><%@include file="/css/admin.css"%></style>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    </head>
    <body>
        <div class="title">
            <font color="white">
                Welcome, ${sessionScope.FULLNAME}
            </font><br/>
            <a href="Logout">Logout</a><br/>
            <h2>FPT University</h2>
        </div>
        
        <form action="SearchQuestion" method="POST" class="search">
            <h1>Search for question</h1>
            Name <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/><br/>
            Status <select name="cbStatus">
                <option></option>
                <option ${param.cbStatus == 'Active'?'selected':''}>Active</option>
                <option ${param.cbStatus == 'Deactive'?'selected':''}>Deactive</option>
            </select><br/>
            Subject <select name="cbSubject">
                <option></option>
                <c:forEach items="${sessionScope.listsub}" var="lsub">
                    <option ${param.cbSubject == lsub.subjectID ?'selected':''}>${lsub.subjectID}</option>
                </c:forEach>
            </select><br/>
            <input type="submit" value="Search" /><br/>
            <nav> 
                <c:url value="createSubject.jsp" var="createSubURL">
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                    <c:param name="index" value="${requestScope.pageIndex}"/>
                </c:url>
                <a href="${createSubURL}">Create subject</a>
                
                <c:url value="createQuestion.jsp" var="createQuesURL">
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                    <c:param name="index" value="${requestScope.pageIndex}"/>
                </c:url>
                <a href="${createQuesURL}">Create question</a>
                
                <c:url value="SearchHistory" var="historyURL">
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                    <c:param name="index" value="${requestScope.pageIndex}"/>
                </c:url>
                <a href="${historyURL}">View history</a>
            </nav>
            
        </form>
            <c:set value="${requestScope.SubjectDTO}" var="sDTO"/>
            
            <h2>${sDTO.subjectName}</h2>
            <c:if test="${not empty requestScope.listQuestion}">  
                <c:forEach items="${requestScope.listQuestion}" var="Qlist" varStatus="counter">
                    <table border="3" style="width:450px;height:300px">
                        <thead>
                            <tr>
                                <th style="width:100px">Question ${counter.count}</th>
                                <th>${Qlist.question_content}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Options</td>
                                <td>
                                    <c:forEach items="${Qlist.listAnswer}" var="Alist">
                                            <i class='fas fa-angle-double-right'></i> ${Alist.answer_content}<br/>
                                    </c:forEach>
                                </td>
                                
                                        
                            </tr>
                            
                            <tr class="result">
                                <td><b>Correct Answer<b/></td>
                                <td>
                                    <b>
                                        ${Qlist.answer_correct}
                                    <b/>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Answer content<b/></td>
                                <td>
                                    <b>
                                        ${Qlist.answer_content}
                                    <b/>
                                </td>
                            </tr>
                            <tr>
                                <td>Subject</td>
                                <td>${Qlist.subjectID}</td>
                            </tr>
                            <tr>
                                <td>Date of create</td>
                                <td>${Qlist.createDate}</td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    <c:if test="${Qlist.status == 'Active'}">
                                        <font color="green">
                                            <b>${Qlist.status} </b>
                                        </font>
                                    </c:if>
                                    <c:if test="${Qlist.status == 'Deactive'}">
                                        <font color="red">
                                           <b>${Qlist.status} </b> 
                                        </font>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Action</td>
                                <td>
                                    <form action="DeleteQuestion">
                                        <input type="hidden" name="QuestionID" value="${Qlist.id}" />
                                        <input type="hidden" name="searchValue" value="${param.txtSearchValue}" />
                                        <input type="hidden" name="searchSubID" value="${param.cbSubject}" />
                                        <input type="hidden" name="searchStatus" value="${param.cbStatus}" />
                                        <input type="hidden" name="index" value="${requestScope.pageIndex}" />
                                        
                                        <input type="submit" value="Delete" />
                                    </form>
                                    <c:url value="LoadEdit" var="editURL">
                                        <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                                        <c:param name="cbStatus" value="${param.cbStatus}"/>
                                        <c:param name="cbSubject" value="${param.cbSubject}"/>
                                        <c:param name="index" value="${requestScope.pageIndex}"/>
                                        
                                        <c:param name="quesID" value="${Qlist.id}"/>
                                        
                                    </c:url> 
                                    <a href="${editURL}">Edit</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </c:forEach>
            </c:if>
                    
            <c:forEach begin="1" end="${requestScope.p}" var="i">
                <c:url value="SearchQuestion" var="paging">
                    <c:param name="index" value="${i}"/>
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <c:param name="cbStatus" value="${param.cbStatus}"/>
                    <c:param name="cbSubject" value="${param.cbSubject}"/>
                </c:url>
                <a href="${paging}">${i}</a>
            </c:forEach>
    </body>
</html>
