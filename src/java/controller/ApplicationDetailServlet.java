/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Application;
import dal.ApplicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employee;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ApplicationDetailServlet", urlPatterns = {"/applicationDetail"})
public class ApplicationDetailServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApplicationDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApplicationDetailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String applicationId_raw = request.getParameter("applicationId");
        
        try {
            int applicationId = Integer.parseInt(applicationId_raw);
            ApplicationDAO adao = new ApplicationDAO();
            Application application = adao.getById(applicationId);                   
            request.setAttribute("application", application);
            
        } catch (NumberFormatException e) {
            System.out.println(e);
        } 
        
        request.getRequestDispatcher("applicationDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        String applicationId_raw = request.getParameter("applicationId");
        String note = request.getParameter("note");
        String action = request.getParameter("action");

        try {
            int applicationId = Integer.parseInt(applicationId_raw);
            ApplicationDAO adao = new ApplicationDAO();
            Application application = adao.getById(applicationId);
            if (action.equals("approve")) {
                adao.approveApplication(applicationId, employee.getEmployeeId(), note);
            } else {
                adao.rejectApplication(applicationId, employee.getEmployeeId(), note);
            }
            request.setAttribute("application", application);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while processing the application");
        }
        
        response.sendRedirect("applicationDetail?applicationId=" + applicationId_raw);
    }

}
