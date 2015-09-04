/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServicePortal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author cb-raghu
 */
public class UpdateUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String address = request.getParameter("Address");
        Connection con = null;
        
        if(firstName.trim().length() == 0 || lastName.trim().length() == 0 || address.trim().length() == 0){
            request.setAttribute("error-info","Invlaid Details");
            request.getRequestDispatcher("/Info.jsp").forward(request, response);
            return;
        }
        
        try{
        con =  Helper.getConnection();
        PreparedStatement ps = con.prepareStatement("update login set FirstName = ?,LastName = ?,Address = ? where email = ?");
        ps.setString(1, firstName); 
        ps.setString(2, lastName); 
        ps.setString(3, address); 
        ps.setString(4, request.getSession(false).getAttribute("email").toString()); 
        int count = ps.executeUpdate();
        Login login = null;
            if(count == 1)
            {
                response.sendRedirect("home.jsp");
            }
            else{
                request.setAttribute("error-info","Update failed");
                request.getRequestDispatcher("/Info.jsp").forward(request, response);
                return;
            }
            con.close();
        }
        catch(Exception ex){
            
        }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
