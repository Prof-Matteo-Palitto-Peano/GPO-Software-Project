package impiegatiDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */

public class Model implements Serializable{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost/TEST";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    
    Statement stmt;
    Connection conn; 


    ResultSet rs = null;
    ResultSetMetaData rsmd = null; // rs.getMetaData();    
    QueryResult rslt = new QueryResult();

    // costruttore inizializza attributo statement(stmt)
    public Model() {


    }
    
    public QueryResult getEmployeesList() {
            String sql;
            ResultSet rs = null;
            ResultSetMetaData rsmd = null; 
            int columnCount = 0;  


        try { // Register JDBC driver
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }       
                try { // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }            
            
            //sql query
            sql = "SELECT id, first, last, age FROM Employees";
            try { // Execute SQL query
                rs = stmt.executeQuery(sql);
                rsmd = rs.getMetaData();
                columnCount = rsmd.getColumnCount(); 
                rslt.colCount = columnCount;
                System.out.println("num emplo: " + columnCount);
                System.out.println(rsmd.getColumnLabel(1));
                System.out.println(rsmd.getColumnLabel(2));
                System.out.println(rsmd.getColumnLabel(3));
                System.out.println(rsmd.getColumnLabel(4));
                
                for(int i=1; i<=columnCount; i++) {
                    String etichetta = rsmd.getColumnLabel(i);
                    rslt.colNames.add(etichetta);
                    //System.out.println(i + " : " + rslt.colNames[i]);
                    System.out.println(i + " : " + etichetta);
                }
                while(rs.next()) {
                    ArrayList<String> employee = new ArrayList<>(columnCount);
                    for(int i=1; i<=columnCount; i++) {
                        employee.add(rs.getString(rsmd.getColumnLabel(i)));
                    }             
                    rslt.employees.add(employee);
                }
                //System.out.println("query results: " + rs);
                // Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
                            
                return rslt;
            } catch( SQLException e) {
                System.out.println("query results Error: " + e);
            } finally {
            //finally block per chiudere le risorse usate
                try {
                    if(stmt!=null) 
                        //System.out.println("statement: " + stmt);
                       stmt.close();
                } catch(SQLException se) {} // nothing we can do

            } //end finally try
            return null;
    } // getEmplyeesList
} // end class Model
