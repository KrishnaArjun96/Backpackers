<%@ page import="JavaBeans.Airline" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/7/17
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Welcome to Backpackers, ${username}</h1>

<br><br>

<form action="/Search" method="post">
    source: <input type="text" name="source" width="25"/>
    destination: <input type="text" name="destination" width="25"/>
    departing: <input type="date" name="departing" width="25"/>
    returning: <input type="date" name="returning" width="25"/>
    <input type="submit" value="Search"/>
</form>

<%--<c:out value="Airlines using JSTL"/>

<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${airlines}" var="air">
        <tr>
            <td><c:out value="${air.id}"/></td>
            <td><c:out value="${air.name}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>

<c:out value="Airlines without using JSTL"/>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Airline> airlines = (ArrayList<Airline>) request.getAttribute("airlines");
        for(int i=0; i<airlines.size(); i++) { %>
        <tr>
            <td><%=airlines.get(i).getId()%></td>
            <td><%=airlines.get(i).getName()%></td>
        </tr>
        <% } %>
    </tbody>
</table>--%>

</body>
</html>
