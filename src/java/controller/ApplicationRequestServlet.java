/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ApplicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Application;
import model.Employee;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ApplicationRequestServlet", urlPatterns = {"/applicationRequest"})
public class ApplicationRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet ApplicationRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApplicationRequestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        
        if(employee == null){ // chưa login 
            response.sendRedirect("login");
        }else { 
        
        request.getRequestDispatcher("applicationRequest.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        
        String title = request.getParameter("title");
        String startDate_raw = request.getParameter("startDate");
        String endDate_raw = request.getParameter("endDate");
        String leaveOnPerson_raw = request.getParameter("leaveOnPerson");
        String reason = request.getParameter("reason");

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        try {
            Date startDate = Date.valueOf(startDate_raw);
            Date endDate = Date.valueOf(endDate_raw);
            int leaveOnPerson = Integer.parseInt(leaveOnPerson_raw);

            ApplicationDAO adao = new ApplicationDAO();
            Application application = new Application(title, startDate, endDate, reason, "In progress", employee.getEmployeeId(),
                     null, null, leaveOnPerson);
            adao.insert(application);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        
        response.sendRedirect("index");

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
