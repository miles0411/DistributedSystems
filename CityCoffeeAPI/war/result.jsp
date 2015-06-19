<%@page contentType="application/json" pageEncoding="UTF-8"%>
Name: <%= request.getAttribute("name")%>
Address: <%= request.getAttribute("address")%>
Phone: <%= request.getAttribute("phone")%>
<%= request.getAttribute("menu")%>


