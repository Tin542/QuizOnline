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
import tinnt.answer.AnswerDAO;
import tinnt.answer.AnswerDTO;
import tinnt.question.QuestionDAO;
import tinnt.question.QuestionDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/TakeQuizServlet"})
public class TakeQuizServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TakeQuizServlet.class);
    
    private final String QUIZ_PAGE = "QuizServlet";
    private final String ERROR_PAGE = "error_page.jsp";
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
        
        String subjectID = request.getParameter("sID");
        String time = request.getParameter("time");
        String noOfQues = request.getParameter("noOfQues");
        int noOfQuestion = 0;//so cau quiz
        String url = ERROR_PAGE;
        
        try {
            HttpSession session = request.getSession();//dung session de luu lis Question da random
            QuestionDAO qDAo = new QuestionDAO();
            AnswerDAO aDAO = new AnswerDAO();
            
            if(noOfQues != null){
                noOfQuestion = Integer.parseInt(noOfQues);
            }
            
            qDAo.getQuestionForQuiz(noOfQuestion, subjectID);
            List<QuestionDTO> lsQuest = qDAo.getListQuestion();
            
            for (QuestionDTO questionDTO : lsQuest) {
                aDAO.getAnswerByQuesID(questionDTO.getId());
                List<AnswerDTO> lsAns = aDAO.getListAnswer();
                questionDTO.setListAnswer(lsAns);
            }
            if(lsQuest != null){
                request.setAttribute("p", noOfQuestion);
                session.setAttribute("lsQuestion", lsQuest);
                session.setAttribute("subjectID", subjectID);//luu subID da lam quiz
                request.setAttribute("time", time);
                url = QUIZ_PAGE;
            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException at TakeQuizServlet: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at TakeQuizServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at TakeQuizServlet: "+ex.getMessage());
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
