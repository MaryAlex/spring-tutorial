<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <c:out value="${user.name}"/>
</c:forEach>
</body>
</html>