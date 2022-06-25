package mainpackege;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Address;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainpackege.beans.SelectBeanLocal;
import mainpackege.beansRepo.RepositoryLocal;

/**
 *
 * @author dkory
 */
@WebServlet(urlPatterns = {""})
public class ViewList extends HttpServlet {
    
    @EJB
        SelectBeanLocal selectBeanLocal;
    @EJB
        RepositoryLocal repo;
    
    List<Address> addressList;
    HttpServletRequest request;
    

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
        this.request = request;
        addressList = repo.getAllAddress();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        addressList = selectBeanLocal.filter(request);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All clients</h1>");
            out.println("<form action=\"\" method=\"GET\">");
            out.println("<p>Filter</p>");
            List<String> allCities = selectBeanLocal.getAllCities(repo.getAllAddress());
            System.out.println(allCities.toString());
            out.println("<p><select size=\"3\" name=\"curCity\">");
            out.println("    <option disabled>Выберите город</option>");
            for (String s : allCities) {
                out.println("    <option value=\"" + s + "\">" + s + "</option>");
            }
            //out.println("    <option value=\"Москва\">Москва</option>");
            //out.println("    <option value=\"Новосибирск\">Новосибирск</option>");
            out.println("</select></p>");
            out.println("<input type=\"text\" name=\"addr\" value=\"\" /><br><br>");
            out.println("<input type=\"submit\" value=\"Apply\"/><br><br>");
            out.println("</form>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>idClient</th>");
            out.println("<th>type</th>");
            out.println("<td>model</td>");
            out.println("<td>ip</td>");
            out.println("<td>city</td>");
            out.println("<td>street</td>");
            out.println("<td>num</td>");
            out.println("<td>subnum</td>");
            out.println("<td>flat</td>");
            out.println("<td>extra</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");
            
           
            for(Address a: addressList) {
                    out.println("<tr>");
                    out.println("<td>" + a.getIdclient().getIdclient()+ "</td>");
                    out.println("<td>" + a.getIdclient().getDevicetype() + "</td>");
                    out.println("<td>" + a.getIdclient().getModel() + "</td>");
                    out.println("<td>" + a.getIdclient().getIp() + "</td>");
                    out.println("<td>" + a.getCity() + "</td>");
                    out.println("<td>" + a.getStreet() + "</td>");
                    out.println("<td>" + a.getNum() + "</td>");
                    out.println("<td>" + a.getSubnum() + "</td>");
                    out.println("<td>" + a.getFlat() + "</td>");
                    out.println("<td>" + a.getExtra() + "</td>");
                    out.println("<td><a href=\"http://localhost:8080/webcustomers/delete?id=" + a.getIdclient().getIdclient() + "\">Delete</a>");
                    
                    out.println("<a href=\"http://localhost:8080/webcustomers/update?idClient=" + a.getIdclient().getIdclient() + "&type=" + a.getIdclient().getDevicetype() +
                           "&model=" + a.getIdclient().getModel() + "&ip=" + a.getIdclient().getIp() +
                            "&idAddress=" + a.getId() + 
                           "&city=" + a.getCity() + "&street=" + a.getStreet() +
                           "&num=" + a.getNum() + "&subnum=" + a.getSubnum()+
                           "&flat=" + a.getFlat() + "&extra=" + a.getExtra() +
                           "\"> Update</a></td>");
                    out.println("</tr>");
           }
        
           out.println("</table><br><br>");
           out.println("<h3><a href=\"http://localhost:8080/webcustomers/create\">Add new client</a><br></h3>");
           out.println("<h3><a href=\"http://localhost:8080/webcustomers/refresh\">Refresh</a></h3>");
           out.println("<h3><a href=\"http://localhost:8080/webcustomers/checksax\">Check sax</a></h3>");
           out.println("<h3><a href=\"http://localhost:8080/webcustomers/checkdom\">Check dom</a></h3>");
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
        response.setContentType("UTF-8");
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
