package mainpackege;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainpackege.beans.ChecksLocal;
import mainpackege.beans.UpdateBeanLocal;

/**
 *
 * @author dkory
 */
@WebServlet(urlPatterns = {"/create"})
public class Create extends HttpServlet {
    
    @EJB
        UpdateBeanLocal updateBeanLocal;
    @EJB
        ChecksLocal checkLocal;

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
            out.println("<title>Servlet Create</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add new client</h1>");
            out.println("<div>");
            out.println("<form action=\"create\" method=\"POST\">");
            //out.println("<b>IdClient:</b>");
            //out.println("<input type=\"text\" name=\"idClient\"/><br><br>");
            out.println("<b>Type:</b>");
            out.println("<input type=\"text\" name=\"type\"/><br><br>");
            out.println("<b>Model:</b>");
            out.println("<input type=\"text\" name=\"model\"/><br><br>");
            out.println("<b>Ip:</b>");
            out.println("<input type=\"text\" name=\"ip\"/><br><br>");
            out.println("<b>City:</b>");
            out.println("<input type=\"text\" name=\"city\"/><br><br>");
            out.println("<b>Street:</b>");
            out.println("<input type=\"text\" name=\"street\"/><br><br>");
            out.println("<b>Num:</b>");
            out.println("<input type=\"text\" name=\"num\"/><br><br>");
            out.println("<b>Subnum:</b>");
            out.println("<input type=\"text\" name=\"subnum\"/><br><br>");
            out.println("<b>Flat:</b>");
            out.println("<input type=\"text\" name=\"flat\"/><br><br>");
            out.println("<b>Extra:</b>");
            out.println("<input type=\"text\" name=\"extra\"/><br><br>");
            out.println("<input type=\"submit\" value=\"Add\"/>");
            out.println("</form>");
            out.println("</div>");
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
        request.setCharacterEncoding("UTF-8");
        //String idClient = "";
        String type = Objects.toString(request.getParameter("type"), "");
        String model = Objects.toString(request.getParameter("model"), "");
        String ip = Objects.toString(request.getParameter("ip"), "");
        String city = Objects.toString(request.getParameter("city"), "");
        String street = Objects.toString(request.getParameter("street"), "");
        String num = Objects.toString(request.getParameter("num"), "");
        String subnum = Objects.toString(request.getParameter("subnum"), "");
        String flat = Objects.toString(request.getParameter("flat"), "");
        String extra = Objects.toString(request.getParameter("extra"), "");
        
        if(!checkLocal.validParams(request)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request,response);
        } else {
        updateBeanLocal.addClient(0, type, model, ip, city,
                street, Integer.parseInt(num), Integer.parseInt(subnum), Integer.parseInt(flat), extra);
        response.sendRedirect("http://localhost:8080/webcustomers/");
        }
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
