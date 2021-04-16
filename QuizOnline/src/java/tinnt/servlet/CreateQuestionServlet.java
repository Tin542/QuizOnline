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
import tinnt.question.QuestionCreateError;
import tinnt.question.QuestionDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateQuestionServlet.class);
    
    private final String CREATE_QUESTION_PAGE = "createQuestion.jsp";
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
        
        String quesID = request.getParameter("txtQuesID");
        String quesContent = request.getParameter("txtQuesContent");
        String ansContent = request.getParameter("txtAnsContent");
        String ansA = request.getParameter("txtAnsA");
        String ansB = request.getParameter("txtAnsB");
        String ansC = request.getParameter("txtAnsC");
        String ansD = request.getParameter("txtAnsD");
        String correctAns = request.getParameter("correctAns");
        String subID = request.getParameter("sid");
        String status = request.getParameter("st");
        
        String searchValue = request.getParameter("txtSearchValue");
        String searchSID = request.getParameter("cbSubject");
        String searchStatus = request.getParameter("cbStatus");
        String index = request.getParameter("index");
        
        Date createDate = new Date();
        String statusID = null;
        
        String url = CREATE_QUESTION_PAGE;
        
        boolean foundErr = false;
        QuestionCreateError err = new QuestionCreateError();
        
        try {
            //check status
            if(status.equals("Active")){
                statusID = "S001";
            }
            if(status.equals("Deactive")){
                statusID = "S003";
            }
            
            //check question ID
            if(quesID.trim().length() > 20){
                foundErr = true;
                err.setQuesIDLenghtErr("ID require maximum 20 chars !");
            }
            
            //check question content
            if(quesContent.trim().length() > 255){
                foundErr = true;
                err.setQuesContwentLenghtErr("Question content requre maximum 255 chars !");
            }
            
            //check all answer
            if(ansA.trim().length() > 255){
                foundErr = true;
                err.setAnsALengthErr("Answer require maximum 255 char !");
            }
            
            if(ansB.trim().length() > 255){
                foundErr = true;
                err.setAnsBLenghtErr("Answer require maximum 255 char !");
            }
            
            if(ansC.trim().length() > 255){
                foundErr = true;
                err.setAnsCLenghtErr("Answer require maximum 255 char !");
            }
            
            if(ansD.trim().length() > 255){
                foundErr = true;
                err.setAnsDLenghtErr("Answer require maximum 255 char !");
            }
            //check answer co trong 4 option hay ko
            if(!correctAns.trim().equalsIgnoreCase(ansA)
                    && !correctAns.trim().equalsIgnoreCase(ansB)
                    && !correctAns.trim().equalsIgnoreCase(ansC)
                    && !correctAns.trim().equalsIgnoreCase(ansD)){
                foundErr = true;
                err.setAnsCorrectNotMatch("Answer not match with options !");
            }
            
            if(foundErr == true){
                request.setAttribute("CREAT_QUES_ERR", err);
            }else{
                QuestionDAO qDAO = new QuestionDAO();
                AnswerDAO aDAO = new AnswerDAO();
                boolean result = qDAO.createQuestion(quesID, quesContent, ansContent, correctAns, createDate, subID, statusID);
                if(result){
                    boolean resultA = false;
                    boolean resultB = false;
                    boolean resultC = false;
                    boolean resultD = false;
                    if(correctAns.equalsIgnoreCase(ansA)){
                        resultA = aDAO.insertAnswer(quesID+"-1", ansA, 1, quesID);
                        resultB = aDAO.insertAnswer(quesID+"-2", ansB, 0, quesID);
                        resultC = aDAO.insertAnswer(quesID+"-3", ansC, 0, quesID);
                        resultD = aDAO.insertAnswer(quesID+"-4", ansD, 0, quesID);
                    }else if(correctAns.equalsIgnoreCase(ansB)){
                        resultA = aDAO.insertAnswer(quesID+"-1", ansA, 0, quesID);
                        resultB = aDAO.insertAnswer(quesID+"-2", ansB, 1, quesID);
                        resultC = aDAO.insertAnswer(quesID+"-3", ansC, 0, quesID);
                        resultD = aDAO.insertAnswer(quesID+"-4", ansD, 0, quesID);
                    }else if(correctAns.equalsIgnoreCase(ansC)){
                        resultA = aDAO.insertAnswer(quesID+"-1", ansA, 0, quesID);
                        resultB = aDAO.insertAnswer(quesID+"-2", ansB, 0, quesID);
                        resultC = aDAO.insertAnswer(quesID+"-3", ansC, 1, quesID);
                        resultD = aDAO.insertAnswer(quesID+"-4", ansD, 0, quesID);
                    }else if(correctAns.equalsIgnoreCase(ansD)){
                        resultA = aDAO.insertAnswer(quesID+"-1", ansA, 0, quesID);
                        resultB = aDAO.insertAnswer(quesID+"-2", ansB, 0, quesID);
                        resultC = aDAO.insertAnswer(quesID+"-3", ansC, 0, quesID);
                        resultD = aDAO.insertAnswer(quesID+"-4", ansD, 1, quesID);
                    }
                    
                    if(resultA == true && resultB == true && resultC == true && resultD == true){
                        request.setAttribute("CREATE_SUCCESS", "Question Created !");
                        url = "SearchQuestionServlet"
                                + "?txtSearchValue=" + searchValue
                                + "&cbStatus=" + searchStatus
                                + "&cbSubject=" + searchSID
                                + "&index=" + index;
                    }
                    
                }
            }
            
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at CreateQuestionServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at CreateQuestionServlet: "+ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.error("SQLException at CreateQuestionServlet: "+msg);
            if (msg.contains("duplicate")) {
                err.setQuesIDExist("Question already exist !");
                request.setAttribute("CREAT_QUES_ERR", err);
            }//end if msg
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
