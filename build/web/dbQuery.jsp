<%-- 
    Document   : dbQuery
    Created on : Apr 26, 2016, 10:12:12 AM
    Author     : mnanda021
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="query" uri="/WEB-INF/tlds/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Data</title>
    </head>
    <body>
        
        <% Connection con_new=(Connection) session.getAttribute("connection");
        %>
        <%
     String str=request.getRequestURL()+"?" ;
     Enumeration<String> Names=request.getParameterNames();
     while(Names.hasMoreElements()){
         String name=Names.nextElement();
         String[] values=request.getParameterValues(name);
         for(int i=0;i<values.length;i++){
             String value=values[i];
             str= str + name + "=" +value;
             
         }
         str=str+"&";
     }
     
boolean status=str.contains("TYPE");
if(status==true){
    String Logout=(String) request.getParameter("TYPE");
    if(Logout.equals("logout")){
        session.invalidate();
        response.sendRedirect("./dbConnection.jsp");
    }
}
     
     %>
        <query:dbQuery con='<%=con_new%>' TYPE="${param.TYPE}" cond="${param.cond}"/>
    </body>
</html>
