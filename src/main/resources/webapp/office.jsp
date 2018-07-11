<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Office</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<jsp:include page="header.jspf"/>
<jsp:useBean id="userDao" class="com.epam.internet_provider.dao.impl.UserDaoImpl"/>
<c:set var="user" scope="session" value="${userDao.getUser(login)}"/>
<div class="balance">
    <c:out value="Добро пожаловать ${user.login}"/>
    <c:out value="Остаток счёта: ${user.cash}"/>
    <c:if test="${role == 1}">
        <p>Слушаю ваши указания, повелитель.</p>
    </c:if>
</div>
</body>
</html>