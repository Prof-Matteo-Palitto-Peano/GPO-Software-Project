package impiegatiDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matteo
 */

public class Model {
 

    // costruttore inizializza attributo statement(stmt)
    public Model() {
 
    }
    
    public DBQuery getEmployeesList() {
            //sql query
            String sqlSELECT = "SELECT id, first, last, age FROM Employees";

            DBQuery rslt = new DBQuery(null, sqlSELECT);


            return rslt;
    } // getEmplyeesList
    
    public DBQuery insertEmployee() {
            //sql query
            String sqlUpdateDB = "INSERT IGNORE INTO Employees VALUES (104, 18, 'Cavallone', 'Marco')";
            String sqlSELECT = "SELECT id, first, last, age FROM Employees";


            DBQuery rslt = new DBQuery(sqlUpdateDB, sqlSELECT);


            return rslt;
    } // getEmplyeesList       
    
    public DBQuery removeEmployee(String id) {
            //sql query
            String sqlUpdateDB = "DELETE FROM Employees WHERE id = " + id;
            String sqlSELECT = "SELECT id, first, last, age FROM Employees";


            DBQuery rslt = new DBQuery(sqlUpdateDB, sqlSELECT);


            return rslt;
    } // getEmplyeesList    
    
} // end class Model
