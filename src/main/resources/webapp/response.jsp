<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<body>
<h2>Hello, ${name}!</h2>
<%
    int day1 = 5;
    switch(day1) {
        case 0:
            out.println("It\'s Sunday.");
            break;
        case 1:
            out.println("It\'s Monday.");
            break;
        case 2:
            out.println("It\'s Tuesday.");
            break;
        case 3:
            out.println("It\'s Wednesday.");
            break;
        case 4:
            out.println("It\'s Thursday.");
            break;
        case 5:
            out.println("It\'s Friday.");
            break;
        default:
            out.println("It's Saturday.");
    }
%>
</body>
</html>
