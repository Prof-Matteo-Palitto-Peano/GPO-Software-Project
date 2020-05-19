<%-- 
    Document   : index
    Created on : May 18, 2020, 6:27:03 PM
    Author     : matteo
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="impiegatiDB.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Viewer JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%  
            DBQuery rs=(DBQuery)request.getAttribute("modelAttr");  
            //out.print("Lista impiegati, "+rs);  
        %>
        <h1>Lista impiegati nel DB</h1>
        <table border="1">
            <thead>
                <tr>
                    <%
                        for(int i=0; i < rs.colCount; i++) {
                            String columnName = rs.colNames.get(i);                       
                    %>
                            <th><%= columnName %></th>
                    <% } // end for loop %>
                </tr>

            </thead>
            <tbody>
                <% 
                    for(ArrayList<String> employee : rs.employees) { // Extract data from result set 
                        //out.print("<tr>");
                %>
                <tr>
                <%
                                for(String colValue : employee) {
                                    out.print("<td>" + colValue + "</td>");
                                } // end for loop 
                %>
                <td><a href="/impiegatiModel?op=rimuovi&id=<%= employee.get(0) %>">rimuovi</a></td></tr>
                <%
                        //out.print("</tr>");
                    } // end for employee loop      
                %>
            </tbody>
        </table>        
    </body>
</html>
