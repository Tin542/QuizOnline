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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tinnt.subject.SubjectCreateError;
import tinnt.subject.SubjectDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateSubjectServlet", urlPatterns = {"/CreateSubjectServlet"})
public class CreateSubjectServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateSubjectServlet.class);
    
    private final String CREATE_PAGE = "createSubject.jsp";
    
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
        
        String subID = request.getParameter("txtsubID");
        String subName = request.getParameter("txtName");
        String sTime = request.getParameter("txtTime");
        String sNum = request.getParameter("txtNo");
        int time = 0;
        int num = 0;
        
        String url = CREATE_PAGE;
        
        SubjectCreateError err = new SubjectCreateError();
        boolean foundErr = false;
        
        try {
            if(subID.length() > 15 || subID.length() < 3){
                foundErr = true;
                err.setSubIdlengthErr("SubjectID require 3-15 chars !");
            }
            
            if(subName.length() > 50 || subName.length() < 3){
                foundErr = true;
                err.setSubNameLengthErr("Subject Name require 3-50 chars !");
            }
            
            if(foundErr){
                request.setAttribute("CREATE_SUB_ERR", err);
            }else{
                if(sTime != null){
                    time = Integer.parseInt(sTime);
                }
                if(sNum != null){
                    num = Integer.parseInt(sNum);
                }
                SubjectDAO sDAO = new SubjectDAO();
                boolean result = sDAO.insertSubject(subID, subName, time, num);
                if(result){
                    request.setAttribute("SUCCESS", "Create Success !");
                    url = CREATE_PAGE;
                }
            }
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.error("SQLException at CreateQuestionServlet: "+msg);
            if (msg.contains("duplicate")) {
                err.setSubIDExist("Subject already exist !");
                request.setAttribute("CREATE_SUB_ERR", err);
            }//end if msg
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at CreateQuestionServlet: "+ex);
        } catch (NamingException ex) {
            LOGGER.error("NamingException at CreateQuestionServlet: "+ex);
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
