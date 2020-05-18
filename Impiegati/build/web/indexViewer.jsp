<%-- 
    Document   : index
    Created on : May 18, 2020, 6:27:03 PM
    Author     : matteo
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="impiegatiDB.QueryResult"%>
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
            QueryResult rs=(QueryResult)request.getAttribute("modelAttr");  
            out.print("Lista impiegati, "+rs);  
        %>
        <h1>Lista nel DB</h1>
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
                        out.print("<tr>");
                                for(String colValue : employee) {
                                    out.print("<td>" + colValue + "</td>");
                                } // end for loop 
                        out.print("</tr>");
                    } // end for employee loop      
                %>
            </tbody>
        </table>        
    </body>
</html>
