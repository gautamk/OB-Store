/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import model.dao.UserDAO;

/**
 *
 * @author gautam
 */
public class Login extends HttpServlet {
    /**
     * Denotes key that contains the errors 
     * during login in the HTTP SESSION. 
     */
    public static final String LOGIN_ERRORS_SESSION_KEY = "login_errors";
    
    public static final String USER_SESSION_KEY = "user";
    
    public static boolean isLoggedIn(HttpSession session){
        if(session.getAttribute(USER_SESSION_KEY) == null)
            return false;
        return true;
    }
    
    public static  boolean loginUser(Users user,HttpSession session){
        if (UserDAO.authenticate(user)) {
            session.setAttribute(USER_SESSION_KEY, user);
            return true;
        }
        session.setAttribute(LOGIN_ERRORS_SESSION_KEY, "Invalid Username or Password");
        return false;
    }
    
    public static void logoutUser(HttpSession session){
        session.setAttribute(USER_SESSION_KEY, null);
    }
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users user = null;
        try{
        user = new Users(request.getParameter("email"), request.getParameter("password"));
        } catch(IllegalArgumentException e){
            request.getSession().setAttribute(LOGIN_ERRORS_SESSION_KEY, e.getMessage());
        }
        HttpSession session = request.getSession();
        if (loginUser(user, session)) {
            response.sendRedirect(request.getContextPath());
        }else{            
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
