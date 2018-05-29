<%@ page import="java.util.List" %>
<%@ page import="org.springframework.validation.ObjectError" %>

<h3>ERROR PAGE</h3>

<h4>The previous action raised the following errors:</h4>
<br>
<ol>
    <%  List<ObjectError> errorsRaised =(List<ObjectError>) request.getAttribute("errors");
        for (ObjectError error : errorsRaised){
    %>
    <li type="square"><%=error.getDefaultMessage()%></li>
    <br>
    <%}%>

</ol>

<form method="get" action=<%=request.getAttribute("goBackTo")%> >
    <input type="submit" value="Go back">
</form>
