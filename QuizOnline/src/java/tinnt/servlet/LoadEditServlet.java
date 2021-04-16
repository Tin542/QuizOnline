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
import tinnt.answer.AnswerDAO;
import tinnt.answer.AnswerDTO;
import tinnt.question.QuestionDAO;
import tinnt.question.QuestionDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoadEditServlet", urlPatterns = {"/LoadEditServlet"})
public class LoadEditServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoadEditServlet.class);
    
    private final String EDIT_PAGE = "editQuestion.jsp";
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
        
        String qID = request.getParameter("quesID");
        
        String searchValue = request.getParameter("txtSearchValue");
        String searchSID = request.getParameter("cbSubject");
        String searchStatus = request.getParameter("cbStatus");
        String index = request.getParameter("index");
        
        QuestionDTO qDTO;
        List<AnswerDTO> lsa = null;
        String url = EDIT_PAGE;
        
        try  {
            if(qID != null){
                AnswerDAO aDAO = new AnswerDAO();
                aDAO.getAnswerByQuesID(qID);
                lsa = aDAO.getListAnswer();
                
                QuestionDAO qDAO = new QuestionDAO();
                qDTO = qDAO.getQuestionByID(qID);
                if(qDTO != null){
                    String status = qDTO.getStatus();
                    if(status.equals("S001")){
                        status = "Active";
                    }else if(status.equals("S003")){
                        status = "Deactive";
                    }
                    
                    request.setAttribute("quest_Content", qDTO.getQuestion_content());
                    request.setAttribute("ansContent", qDTO.getAnswer_content());
                    request.setAttribute("quest_Correct", qDTO.getAnswer_correct());
                    request.setAttribute("SelectStatus", status);
                    request.setAttribute("subID", qDTO.getSubjectID());
                }
            }
            request.setAttribute("lisAns", lsa);
            request.setAttribute("qID", qID);
            
            request.setAttribute("txtSearchValue", searchValue);
            request.setAttribute("cbSubject", searchSID);
            request.setAttribute("cbStatus", searchStatus);
            request.setAttribute("index", index);

        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at LoadEditServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at LoadEditServlet: "+ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error("SQLException at LoadEditServlet: "+ex.getMessage());
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
