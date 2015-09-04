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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cb-raghu
 */
public class SaveSignUp extends HttpServlet {

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
            throws ServletException, IOException{
        String infoText = "";
        try{
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("email-confirm");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password-confirm");
        
        if(firstName.trim().length() == 0 || lastName.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0){
            request.setAttribute("error-info","Invlaid Details");
            request.getRequestDispatcher("/Info.jsp").forward(request, response);
            return;
        }
        if(!email.equalsIgnoreCase(confirmEmail)){
            request.setAttribute("error-info","Email mismatch");
            request.getRequestDispatcher("/Info.jsp").forward(request, response);
            return;
        }
        if(!password.equals(confirmPassword)){
            request.setAttribute("error-info","Password mismatch");
            request.getRequestDispatcher("/Info.jsp").forward(request, response);
            return;
        }
        int flag = saveSignUpDetails(email, firstName, lastName, password);
        infoText = flag == 1 ? "successfully signed up" : "Error in sign up";        
        
        return;
        }
        catch(Exception ex){
            infoText = ex.getMessage();
        }
        finally{
        
        request.setAttribute("error-info",infoText);
        request.getRequestDispatcher("/Info.jsp").forward(request, response);
        }
    }
    
    protected  int saveSignUpDetails(String email,String firstName,String lastName,String password) throws SQLException,ClassNotFoundException{
        Connection con =  Helper.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into login(Email,FirstName,LastName,Password) values(?,?,?,md5(?))");
        ps.setString(1, email);
        ps.setString(2, firstName);
        ps.setString(3, lastName);
        ps.setString(4, password);       
        
        int flag =  ps.executeUpdate();
        con.close();
        
        return  flag;
        
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
