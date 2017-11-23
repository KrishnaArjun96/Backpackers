<%@ page import="Classes.Data" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Rahul
  Date: 11/01/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome!</title>
  </head>
  <body>

  <%
    try {
      Data.Refresh();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  %>

  <!--
    to print all the airports in the master list:

    for(Airport airport: AIRPORTS) {
      System.out.println(airport.toString());
    }
  -->

  Backpackers!!!
  </body>
</html>
