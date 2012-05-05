/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Register extends HttpServlet {

    public static final String REGISTER_ERROR_SESSION_KEY = "register_errors";
    public static final String FORM_DATA_KEY = "form_data";

    public static boolean registerUser(
            Users user, HttpSession session) {
        try {
            UserDAO.register(user);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute(REGISTER_ERROR_SESSION_KEY, "Email already in use, Please choose a different one");
            return false;
        }
        return true;
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
        response.setContentType("text/html;charset=UTF-8");

        try {
            Users user = new Users(
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("address"),
                    request.getParameter("phone"));
            registerUser(user, request.getSession());
            request.getSession().setAttribute(FORM_DATA_KEY, user);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch(IllegalArgumentException e){
            request.getSession().setAttribute(Register.REGISTER_ERROR_SESSION_KEY, e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } finally {
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
