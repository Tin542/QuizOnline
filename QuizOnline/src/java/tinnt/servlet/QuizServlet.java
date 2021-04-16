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
import tinnt.answer.AnswerDTO;
import tinnt.question.QuestionDTO;
import tinnt.subject.SubjectDAO;
import tinnt.subject.SubjectDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(QuizServlet.class);
    
    private final String QUIZ_PAGE = "quiz.jsp";
    private final String RESULT_PAGE = "QuizResultServlet";
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
        
        String time = request.getParameter("timeleft");
        String index = request.getParameter("quizIndex");
        String action = request.getParameter("doQuiz");
        String QID = request.getParameter("questionID");
        String listAnswer = request.getParameter("rdAnswer");
        
        HttpSession session = request.getSession();
        List<QuestionDTO> lsq = (List<QuestionDTO>) session.getAttribute("lsQuestion");
        String subjectID = (String) session.getAttribute("subjectID");
        
        
        
        String url = QUIZ_PAGE;
        
        try {
            SubjectDAO sDAO = new SubjectDAO();
            SubjectDTO sDTO = sDAO.getSubjectByID(subjectID);
            
            if (listAnswer != null) {
                for (QuestionDTO question : lsq) {
                    if (question.getId().equals(QID)) {
                        question.setStudentAnswer(listAnswer);
                    }
                }
            }

            if (index == null) {
                index = "0";
            }

            int indexQ = Integer.parseInt(index);//0

            if (time == null) {
                time = request.getParameter("time"); 
                request.setAttribute("time", Integer.parseInt(time) * 60); //300s
            } else {
                // update time
                // s =  min * 60 = n(s) + s 1 04minute59second 
                int Second = Integer.parseInt(time.substring(0, time.indexOf("minute"))) * 60 + Integer.parseInt(time.substring(7, time.indexOf("second")));
                request.setAttribute("time", Second);

                if(action == null){
                    action = "";
                }
                if (indexQ != 0 && action.equals("")) {
                    indexQ -= 1;
                }

                if (action.equals("Next")) {
                    indexQ++;
                }else if (action.equals("Previous")) {
                    indexQ--;
                }else if (action.equals("Submit")) {
                    url = RESULT_PAGE;
                }else if (!action.equals("")) {
                    indexQ = Integer.parseInt(action) - 1;
                }

            }
            String QuestionID = lsq.get(indexQ).getId();
            String QuestionContent = lsq.get(indexQ).getQuestion_content();
            String savedAnswer = lsq.get(indexQ).getStudentAnswer();
            List<AnswerDTO> answerContent = lsq.get(indexQ).getListAnswer();

            request.setAttribute("quizsize", sDTO.getNoOfQuestion());
            request.setAttribute("listAnswer", answerContent);
            request.setAttribute("Student", savedAnswer);
            request.setAttribute("questionID", QuestionID);
            request.setAttribute("quizIndex", String.valueOf(indexQ));
            request.setAttribute("content", QuestionContent);
            
        } catch (SQLException ex) {
            LOGGER.error("SQLException at QuizServlet: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at QuizServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at QuizServlet: "+ex.getMessage());
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
