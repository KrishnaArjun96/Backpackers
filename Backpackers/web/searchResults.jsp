<%@ page import="JavaBeans.Airline" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="JavaBeans.Airport" %>
<%--
  Created by IntelliJ IDEA.
  User: Rahul
  Date: 11/12/17
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight Results</title>
</head>
<body>
<h1>Got ${pathCount} results:   </h1>
<%    ArrayList<ArrayList<Airport>> paths = (ArrayList<ArrayList<Airport>>) request.getAttribute("paths"); %>

<% for(int i=0; i<paths.size(); i++) { %>
<h5>Got <%= paths.get(i).size()%></h5>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
    </tr>
    </thead>
    <tbody>
    <% for(int j=0; j<paths.get(i).size(); j++) {%>
    <tr>
        <td><%= paths.get(i).get(j).getId()%></td>
        <td><%= paths.get(i).get(j).getName()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } %>
</body>
</html>
