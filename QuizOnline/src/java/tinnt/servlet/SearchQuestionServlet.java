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
import org.apache.log4j.Logger;
import tinnt.answer.AnswerDAO;
import tinnt.answer.AnswerDTO;
import tinnt.question.QuestionDAO;
import tinnt.question.QuestionDTO;
import tinnt.subject.SubjectDAO;
import tinnt.subject.SubjectDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchQuestionServlet", urlPatterns = {"/SearchQuestionServlet"})
public class SearchQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchQuestionServlet.class);
    
    private final String ADMIN_PAGE = "admin.jsp";
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
        
        String searchValue = request.getParameter("txtSearchValue");
        String searchStatus = request.getParameter("cbStatus");
        String searchSubID = request.getParameter("cbSubject");
        String index = request.getParameter("index");
        int pageIndex = 1;
        if(index != null){
            pageIndex = Integer.parseInt(index);
        }
        String url = ADMIN_PAGE;
        List<QuestionDTO> listQuestion;
        int countPage;
        
        try {
            //lay subject
            SubjectDAO sDAO = new SubjectDAO();
            SubjectDTO sDTO = sDAO.getSubjectByID(searchSubID);
   
            QuestionDAO qDAO = new QuestionDAO();
            AnswerDAO aDAO = new AnswerDAO();
            
            if(searchValue == null){
                searchValue = "";
            }
            if(searchStatus == null){
                searchStatus = "";
            }
            if(searchSubID == null){
                searchSubID = "";
            }
            
            if(searchValue.equals("") && searchStatus.equals("") && searchSubID.equals("")){
                countPage = qDAO.pagingAllQuestion();
                qDAO.getAllQuestion(pageIndex);
                listQuestion = qDAO.getListQuestion();
            }else{
                //dem Question tim dc 
                countPage = qDAO.pagingFindByFilter(searchSubID, searchValue, searchStatus);
                //lay list question
                qDAO.findByFilter(pageIndex,searchSubID, searchValue, searchStatus);
                listQuestion = qDAO.getListQuestion(); 
            }
            
            //lay 4 dap an cua tung cau hoi
            for (QuestionDTO questionDTO : listQuestion) {
               aDAO.getAnswerByQuesID(questionDTO.getId());
               List<AnswerDTO> listAnswer = aDAO.getListAnswer();
               questionDTO.setListAnswer(listAnswer);
            }
            
            request.setAttribute("p", countPage);
            request.setAttribute("SubjectDTO", sDTO);
            request.setAttribute("listQuestion", listQuestion);
            request.setAttribute("pageIndex", pageIndex);
            
            
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at SearchQuestionServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at SearchQuestionServlet: "+ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error("SQLException at SearchQuestionServlet: "+ex.getMessage());
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
