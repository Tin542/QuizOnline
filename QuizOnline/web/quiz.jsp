<%-- 
    Document   : quiz
    Created on : Feb 15, 2021, 4:34:55 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
        <style><%@include file="/css/quiz.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
    </head>
    
    <script type="text/javascript">
            var Second = ${requestScope.time};//300
            var min = Math.floor(Second / 60);//5p00s
          

            function System() {
                var count1 = document.getElementById("timeremain");
                if ((Second % 60) == 0) {
                    min = min - 1;
                }

                count1.value = (min) + "minute" + ((Second - 1) % 60) + "second";
                document.getElementById("time").value = (min) + "minute" + ((Second - 1) % 60) + "second";
                Second = Second -1;
                if ((min * 60) == 0 && Second == 0) {
                    Helicop();
                }
                setTimeout("System()", 1000);

            }
            function Helicop()
            {
                window.location.replace("QuizResult");// TIME OUT RES TO GRADE CONTROLLER
            }
        </script>
        <body onload="System()">
            <div class="title">
                <h2>Take Quiz</h2>
                <font color="red">
                    Welcome, ${sessionScope.FULLNAME}
                </font><br/>
            </div>
            Time left: <input type="text" id="timeremain" disabled="" />
            <form action="Quiz" method="POST">
                <div class="content">
                    <input type="hidden"  name="timeleft" id="time"/>
                    <input type="hidden" name="questionID" value="${requestScope.questionID}" />    
                    <input type="hidden" name="quizIndex" value="${requestScope.quizIndex}" />   

                    <p id="Contend"><b>Question ${requestScope.quizIndex + 1} : ${requestScope.content}</b></p>
                    <c:forEach items="${requestScope.listAnswer}" var="la">
                        <c:choose>
                            <c:when test="${requestScope.Student eq la.answer_content}">
                                <input type="radio" name="rdAnswer" value="${la.answer_content}" checked="true"/>
                                ${la.answer_content}<br/>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="rdAnswer" value="${la.answer_content}"/>
                                ${la.answer_content}<br/>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </div>
                
                <div class="btn">
                    <c:if test="${requestScope.quizIndex > 0}">
                        <input type="submit" name="doQuiz" value="Previous" />
                    </c:if>
                    <c:if test="${requestScope.quizIndex + 1 < requestScope.quizsize}">
                        <input type="submit" name="doQuiz" value="Next" />
                    </c:if>
                        <br>
                    <c:forEach begin="1" end="${requestScope.quizsize}" var="i"> 
                        <input type="submit" name="doQuiz" value="${i}" />
                    </c:forEach>
                        <br/>
                        <input type="submit" name="doQuiz" value="Submit" />
                </div>
                
            </form>


    </body>
</html>
