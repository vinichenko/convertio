<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your file</title>
</head>
<body>
    <% if ((Boolean)request.getAttribute("exists")) { %>
        <a href="/?name=<%=request.getParameter("id")%>.mp3">Link to your file</a>
    <%} else {%>
    Work in progress...
    <% } %>
</body>
</html>