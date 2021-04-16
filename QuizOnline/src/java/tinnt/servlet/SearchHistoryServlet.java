/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinnt.result.ResultDAO;
import tinnt.result.ResultDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchHistoryServlet.class);
    
    private final String HISTORY_PAGE = "history.jsp";
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
        
        String subName = request.getParameter("cbSubName");
        String index = request.getParameter("index");
        int pageIndex = 1;
        if(index != null){
            pageIndex = Integer.parseInt(index);
        }
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        String studentRole = (String) session.getAttribute("STUDENT");
        String adminRole = (String) session.getAttribute("ADMIN");
        int countPage = 0;
        List<ResultDTO> lsr = null;
        
        String url = HISTORY_PAGE;
        
        if(subName == null){
            subName = "";
        }
        //giu gia tri search cua trang admin
        String searchValue = request.getParameter("txtSearchValue");
        String searchSID = request.getParameter("cbSubject");
        String searchStatus = request.getParameter("cbStatus");
        String homeIndex = request.getParameter("index");
        
        try {
            ResultDAO rDAO = new ResultDAO();
            
            if(studentRole != null){
                if(subName.equals("")){
                    countPage = rDAO.pagingAllHistoryForStudent(email);
                    rDAO.getAllHistoryForStudent(email, pageIndex);
                    lsr = rDAO.getListResult();
                }else{
                    countPage = rDAO.pagingSearchByEmailAndSubName(email, subName);
                    rDAO.searchByEmailAndSubName(pageIndex, email, subName);
                    lsr = rDAO.getListResult();
                }
                
            }
            if(adminRole!= null){
                if(subName.equals("")){
                    countPage = rDAO.pagingAllHistoryForAdmin();
                    rDAO.getAllHistoryForAdmin(pageIndex);
                    lsr = rDAO.getListResult();
                }else{
                    countPage = rDAO.pagingSearchBySubName(subName);
                    rDAO.searchBySubName(pageIndex, subName);
                    lsr = rDAO.getListResult();
                }
                
            }
            
            request.setAttribute("p", countPage);
            request.setAttribute("listResult", lsr);
            request.setAttribute("txtSearchValue", searchValue);
            request.setAttribute("cbSubject", searchSID);
            request.setAttribute("cbStatus", searchStatus);
            request.setAttribute("index", homeIndex);
            
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at SearchHistoryServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at SearchHistoryServlet: "+ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error("SQLException at SearchHistoryServlet: "+ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
