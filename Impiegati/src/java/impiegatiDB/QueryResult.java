
package impiegatiDB;
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
public class QueryResult {
    public int colCount;
    public ArrayList<String> colNames = new ArrayList<>();
    public ArrayList<ArrayList<String>> employees = new ArrayList<>();   
}
