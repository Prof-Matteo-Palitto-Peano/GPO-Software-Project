/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impiegatiDB;


import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author matteo
 */
@WebServlet(name = "impiegatiModel", urlPatterns = {"/impiegatiModel"})
public class impiegatiController extends HttpServlet {
    DBQuery rs = null;
    Model db = new Model();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try { 
            String op=request.getParameter("op");
            if(op == "rimuovi") {
                String id=request.getParameter("id");
                rs = db.removeEmployee(id);
            } else {
                rs = db.insertEmployee();               
            }
            //rs = db.getEmployeesList();
            System.out.println("NUM> " + rs.employees.size());
            
            //Utilizza il Dispatcher per passare la lista al View Layer
            //aggiungo l'attributo che chiamo modelAttr il cui valore e' l'oggetto modelObj
            request.setAttribute("modelAttr", rs);
            //invio la richiesta alla pagina "indexViewer.jsp"
            RequestDispatcher rd=request.getRequestDispatcher("indexViewer.jsp");          
            rd.forward(request, response); 

        // } catch (ServletException | IOException ex) {
        } catch (Exception ex) {
            Logger.getLogger(impiegatiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } // end doGet
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
