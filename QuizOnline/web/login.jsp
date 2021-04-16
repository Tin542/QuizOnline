<%-- 
    Document   : login
    Created on : Feb 1, 2021, 4:11:32 PM
    Author     : ADMIN
--%>

<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style><%@include file="/css/login.css"%></style>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="login">
           <h1>Login Page</h1>
           <form action="Login" method="POST">
               <c:set value="${requestScope.INVALID}" var="invalid"/>
               <input type="text" name="email" value="" placeholder="Email" required/><br/>
               <input type="password" name="password" value="" placeholder="Password" required/><br/>
                <c:if test="${not empty invalid}">
                   <font color="red">${invalid}</font>
               </c:if>
                <input type="submit" value="Login" name="btAction" class="w3-button w3-blue"/><br/>
                <a href="createNewAccount.jsp">Register Account</a><br/>
                <div class="pt-10 text-center">
                        <div class="mb-2">Or login with</div>
                        <a class="login-social-item m-auto mt-3" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/QuizOnline/GoogleLogin&response_type=code
                           &client_id=371425103371-4h0jevda1dsu2kd9cs7f7i5qe94dlgvl.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fa fa-google"></i>
                        </a>  
                </div>
            </form>
           
       </div>      
    </body>
</html>
