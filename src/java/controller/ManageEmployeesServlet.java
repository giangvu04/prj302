/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageEmployeesServlet", urlPatterns = {"/manageEmployees"})
public class ManageEmployeesServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageEmployeesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageEmployeesServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) { // chưa login 
            response.sendRedirect("login");
        } else {
            EmployeeDAO edao = new EmployeeDAO();
            List<Employee> employees = new ArrayList<>();
            if(employee.getRoleId()==1){ //giam doc
                employees = edao.getAll();
            }else if (employee.getRoleId()==2 ){//truong phong
                employees = edao.getEmloyeesForDivisionLeader(employee);
            }else if(employee.getRoleId()==3){//trong nhom
                employees = edao.getEmloyeesForTeamLeader(employee);
                
            }else{
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("manageEmployees.jsp").forward(request, response);
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
