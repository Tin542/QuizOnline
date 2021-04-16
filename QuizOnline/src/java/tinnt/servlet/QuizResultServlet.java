/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import tinnt.question.QuestionDTO;
import tinnt.result.ResultDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizResultServlet", urlPatterns = {"/QuizResultServlet"})
public class QuizResultServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(QuizResultServlet.class);
    
    private final String RESULT_PAGE = "result.jsp";
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
        
        
        String url = ERROR_PAGE;
        
        try {
            //lay list Question da luu trong session
            HttpSession session = request.getSession();
            List<QuestionDTO> lsQuest = (List<QuestionDTO>) session.getAttribute("lsQuestion");
            
            //dem so cau dung
            int noOfCorrect = 0;
            for (QuestionDTO questionDTO : lsQuest) {
                String answer = questionDTO.getStudentAnswer();
                if(answer == null){
                    answer = "";
                }
                if(answer.equalsIgnoreCase(questionDTO.getAnswer_correct())){
                    noOfCorrect++;
                }
            }
            
            //tinh diem
            float grade;
            grade = noOfCorrect*10/lsQuest.size();
            
            //luu ket qua vao database
            ResultDAO rDAO = new ResultDAO();
            String subID = (String) session.getAttribute("subjectID");//lay session da luu o TakeQuizServlet
            String email = (String) session.getAttribute("EMAIL");//lay session da luu o LoginServlet
            String name = (String) session.getAttribute("FULLNAME");//lay session da luu o LoginServlet
            String lastID = rDAO.getLastResultID(email);
            String resultID;
            if(lastID != null){
                String[] tmp = lastID.split("-");
                resultID = email + "-" + (Integer.parseInt(tmp[1])+1);
            }else{
                resultID = email + "-1";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date createDate = new Date();
            boolean result = rDAO.insertResult(resultID, email, name, subID, grade, noOfCorrect, sdf.format(createDate));
            if(result){
                request.setAttribute("noOfCorrect", noOfCorrect);
                request.setAttribute("grade", grade);
                url = RESULT_PAGE;
            }
            
        } catch (SQLException ex) {
            LOGGER.error("SQLException at QuizResultServlet: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at QuizResultServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at QuizResultServlet: "+ex.getMessage());
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
