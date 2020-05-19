
package impiegatiDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 * Struttura dati per memorizzare il risultato della query al DB
 * 
 * Esegue la query e memorizza il risultato
 */
public class DBQuery {
    public int colCount; // numero colonne nella tabella del DB
    public ArrayList<String> colNames = new ArrayList<>(); //il nome di ogni colonna
    public ArrayList<ArrayList<String>> employees = new ArrayList<>(); // i risultao della query
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost/TEST";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    
    Statement stmt;
    Connection conn; 


    public DBQuery(String sqlUpdateDB, String sqlSELECT) {
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

            try { // Execute SQL query
                if(sqlUpdateDB != null) {
                    System.out.println("EXECUTING SQL: " + sqlUpdateDB);
                    stmt.executeUpdate(sqlUpdateDB);
                }
                rs = stmt.executeQuery(sqlSELECT);
                rsmd = rs.getMetaData();
                columnCount = rsmd.getColumnCount(); 
                this.colCount = columnCount;
                
                // Nomi delle colonne
                for(int i=1; i<=columnCount; i++) {
                    String etichetta = rsmd.getColumnLabel(i);
                    this.colNames.add(etichetta);
                    //System.out.println(i + " : " + rslt.colNames[i]);
                    System.out.println(i + " : " + etichetta);
                }
                
                // i valori della query
                while(rs.next()) {
                    ArrayList<String> employee = new ArrayList<>(columnCount);
                    for(int i=1; i<=columnCount; i++) {
                        employee.add(rs.getString(rsmd.getColumnLabel(i)));
                    }             
                    this.employees.add(employee);
                }
                //System.out.println("query results: " + rs);
                // Clean-up environment
                rs.close();
                stmt.close();
                conn.close();                          
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
    }

    
}
