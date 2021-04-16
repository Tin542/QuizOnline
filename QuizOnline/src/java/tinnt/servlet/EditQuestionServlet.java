/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tinnt.answer.AnswerDAO;
import tinnt.question.QuestionDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditQuestionServlet", urlPatterns = {"/EditQuestionServlet"})
public class EditQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(EditQuestionServlet.class);
    
    private final String EDIT_QUESTION_PAGE = "editQuestion.jsp";
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
        
        String quesID = request.getParameter("txtQID");
        String quesContent = request.getParameter("txtQuestionContent");
        String ansContent = request.getParameter("txtAnswerContent");
        String optionA = request.getParameter("txtAnsOption1");
        String optionB = request.getParameter("txtAnsOption2");
        String optionC = request.getParameter("txtAnsOption3");
        String optionD = request.getParameter("txtAnsOption4");
        String correctAns = request.getParameter("corectAns");
        String subID = request.getParameter("sID");
        String status = request.getParameter("SelectStatus");
        Date doc = new Date();
        String statusID = null;
        
        String url = EDIT_QUESTION_PAGE;
        
        String searchValue = request.getParameter("searchValue");
        String searchSID = request.getParameter("searchSubID");
        String searchStatus = request.getParameter("searchStatus");
        String index = request.getParameter("index"); 
        
        try {
            if(status.equals("Active")){
                statusID = "S001";
            }
            if(status.equals("Deactive")){
                statusID = "S003";
            }
            
            QuestionDAO qDAO = new QuestionDAO();
            boolean result = qDAO.editQuestion(quesID, quesContent, ansContent, correctAns, doc, subID, statusID);
            if(result){
                AnswerDAO aDAO = new AnswerDAO();
                boolean resultA = false;
                boolean resultB = false;
                boolean resultC = false;
                boolean resultD = false;
                if(correctAns.equalsIgnoreCase(optionA)){
                    resultA = aDAO.editAnswer(optionA, 1, quesID+"-1");
                    resultB = aDAO.editAnswer(optionB, 0, quesID+"-2");
                    resultC = aDAO.editAnswer(optionC, 0, quesID+"-3");
                    resultD = aDAO.editAnswer(optionD, 0, quesID+"-4");
                }else if(correctAns.equalsIgnoreCase(optionB)){
                    resultA = aDAO.editAnswer(optionA, 0, quesID+"-1");
                    resultB = aDAO.editAnswer(optionB, 1, quesID+"-2");
                    resultC = aDAO.editAnswer(optionC, 0, quesID+"-3");
                    resultD = aDAO.editAnswer(optionD, 0, quesID+"-4");
                }else if(correctAns.equalsIgnoreCase(optionC)){
                    resultA = aDAO.editAnswer(optionA, 0, quesID+"-1");
                    resultB = aDAO.editAnswer(optionB, 0, quesID+"-2");
                    resultC = aDAO.editAnswer(optionC, 1, quesID+"-3");
                    resultD = aDAO.editAnswer(optionD, 0, quesID+"-4");
                }else if(correctAns.equalsIgnoreCase(optionD)){
                    resultA = aDAO.editAnswer(optionA, 0, quesID+"-1");
                    resultB = aDAO.editAnswer(optionB, 0, quesID+"-2");
                    resultC = aDAO.editAnswer(optionC, 0, quesID+"-3");
                    resultD = aDAO.editAnswer(optionD, 1, quesID+"-4");
                }
                if(resultA == true && resultB == true && resultC == true && resultD == true){
                    url = "SearchQuestionServlet"
                                + "?txtSearchValue=" + searchValue
                                + "&cbStatus=" + searchStatus
                                + "&cbSubject=" + searchSID
                                + "&index=" + index; 
                }
                
            }
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at EditQuestionServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at EditQuestionServlet: "+ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error("SQLException at EditQuestionServlet: "+ex.getMessage());
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
