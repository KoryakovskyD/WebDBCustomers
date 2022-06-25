/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege;


import entity.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainpackege.beans.MyFilterLocal;

/**
 *
 * @author denis
 */
@WebServlet(name = "CheckSAX", urlPatterns = {"/checksax"})
public class CheckSAX extends HttpServlet {
    @EJB
        MyFilterLocal filter;

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
        String filterName = Objects.toString(request.getParameter("filterName"), "");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckSAX</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>SAX parser</h1>");
            out.println("<form action=\"checksax\" method=\"GET\">");
            out.println("<input type=\"text\" name=\"filterName\" />");
            out.println("<input type=\"submit\" value=\"filter\" />");
            out.println("</form>");
            filter.createXML();
             int flag=0;
            if(!filterName.isEmpty()){
                for(Client c : filter.filterSAX(filterName)){
                    out.println("<h2>" + c + "</h2>");
                    flag=1;
                }
                if (flag==0) {
                    out.println("<h2>Сведения с параметром \"" + filterName + "\" отсутствуют!</h2>");
                }
            }
            
            if (filterName.isEmpty()) out.println("<h2>Введите ключевое слово для поиска</h2>");
            
            out.println("</body>");
            out.println("</html>");
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
