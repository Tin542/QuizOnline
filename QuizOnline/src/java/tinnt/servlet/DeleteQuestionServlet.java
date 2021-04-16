/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tinnt.question.QuestionDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteQuestionServlet", urlPatterns = {"/DeleteQuestionServlet"})
public class DeleteQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteQuestionServlet.class);
    
    private final String ADMIN_PPAGE = "admin.jsp";
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
        PrintWriter out = response.getWriter();
        
        String QuestionID = request.getParameter("QuestionID");
        String searchValue = request.getParameter("searchValue");
        String subID = request.getParameter("searchSubID");
        String status = request.getParameter("searchStatus");
        String index = request.getParameter("index");
        String url = ADMIN_PPAGE;
        
        try {
            QuestionDAO qDAO = new QuestionDAO();
            boolean result = qDAO.deleteQuestion(QuestionID);
            if(result){
                url = "SearchQuestion"
                        + "?txtSearchValue=" + searchValue
                        + "&cbStatus=" + status
                        + "&cbSubject=" + subID
                        + "&index=" + index;
            }
            
        } catch (SQLException ex) {
            LOGGER.error("SQLException at DeleteQuestionServlet: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at DeleteQuestionServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at DeleteQuestionServlet: "+ex.getMessage());
        }finally{
            response.sendRedirect(url);
            out.close();
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
