<%@ page import="JavaBeans.Airline" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="JavaBeans.Airport" %>
<%@ page import="JavaBeans.Option" %>
<%@ page import="static webapp.Search.convertTimeFormat" %>
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
<%    ArrayList<Option> options = (ArrayList<Option>) request.getAttribute("options"); %>
<% if(options.size() <= 0) { %>
    <h1>NO RESULTS FOUND!!</h1>
<% } else { %>
    <h1>Results for <%=options.get(0).getLegs().get(0).getOrigin().getCity()%> to <%=options.get(0).getLegs().get(options.get(0).getLegs().size() - 1).getDestination().getCity()%></h1>
    <% for(Option option: options) { %>
    <table>
        <tbody>
        <tr>
            <td>
                <% for(Airline airline: option.getAirlines()) { %>
                <%= airline.getName() %> <br>
                <% }%>
            </td>
            <td>
                <%=option.getLegs().get(0).getOrigin().getId()%> - <%=option.getLegs().get(option.getLegs().size() - 1).getDestination().getId()%>
                <br>
                <%=option.getLegs().get(0).getDeparture()%> - <%=option.getLegs().get(option.getLegs().size() - 1).getArrival()%>
            </td>
            <td>
                Stops: <%= option.getLegs().size() - 1%>
                <br>
                <%= option.getLegs().get(0).getDestination().getId()%>
                <% for(int i=1; i<option.getLegs().size()-1; i++) { %>
                , <%= option.getLegs().get(i).getDestination().getId()%>
                <% } %>
            </td>
            <td>
                Total Flight Duration: <%= convertTimeFormat(option.getTotalDuration())%>
            </td>
            <td>
                $ <%= option.getTotalFare() %>
            </td>
        </tr>
        </tbody>
    </table>
    <hr>
    <% }%>
<% } %>
</body>
</html>
