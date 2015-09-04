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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cb-raghu
 */
public class Authenticate extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");        
        try{
            HttpSession session; /*=  request.getSession(false);
           
            if(session != null){
                request.setAttribute("error-info","Log Out from the current session");
                request.getRequestDispatcher("/Info.jsp").forward(request, response);
                return;
            }*/
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String emailId;
            if(email != null && password != null){            
            emailId = checkIfValidUser(email, password);
            if(emailId == null){
                request.setAttribute("error-info","Invalid user credentials.");
                request.getRequestDispatcher("/Info.jsp").forward(request, response);
                return;
            }            
            session = request.getSession(true);
            //updateSession(session.getId(),email);
            Cookie cookie = new Cookie("login_token", session.getId());
            response.addCookie(cookie);
            session.setAttribute("email", emailId);
            response.sendRedirect("home.jsp"); 
        }
            
            
        }
        catch(Exception ex){
            System.out.println("ex" + ex); 
        }
    }
    
    private boolean updateSession(String sessionId,String email)throws SQLException,ClassNotFoundException{
        Connection con =  Helper.getConnection();
        PreparedStatement ps = con.prepareStatement("update login set sessionId = ? where email = ?");
        ps.setString(1, sessionId);
        ps.setString(2, email);
        int flag = ps.executeUpdate();
        return (flag == 1 ? true: false);
    }
    
    private String checkIfValidUser(String email,String password) throws SQLException,ClassNotFoundException {
        String emailId = null;
        Connection con =  Helper.getConnection();
        PreparedStatement ps = con.prepareStatement("Select email from login where email = ? and password = md5(?)");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs =  ps.executeQuery();
        if(rs != null && rs.next())
        {
            return rs.getString(1);
        }        
        con.close();
        return emailId;
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
