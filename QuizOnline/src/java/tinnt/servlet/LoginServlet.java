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
import tinnt.subject.SubjectDAO;
import tinnt.subject.SubjectDTO;
import tinnt.user.UserDAO;
import tinnt.user.UserDTO;
import tinnt.utils.MyUtils;
import org.apache.log4j.Logger;
/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    
    private final String ADMIN_PAGE = "SearchQuestionServlet";
    private final String STUDENT_PAGE = "student.jsp";
    private final String LOGIN_PAGE = "login.jsp";
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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String url = LOGIN_PAGE;
        
        try {
            UserDAO uDAO = new UserDAO();
            UserDTO uDTO = uDAO.checkLogin(email, MyUtils.encryptPassword(password));
            if(uDTO != null){
                //get user's name and email
                String fullname = uDTO.getFullname();
                session.setAttribute("EMAIL", email);
                session.setAttribute("FULLNAME", fullname);
                
                //get list subject
                SubjectDAO sDAO = new SubjectDAO();
                sDAO.getAllSubject();
                List<SubjectDTO> listSubject = sDAO.getListSubject();
                session.setAttribute("listsub", listSubject);
                
                //check student or admin
                String role = uDTO.getRole();
                if(role.equals("Student")){
                    session.setAttribute("STUDENT", role);
                    url = STUDENT_PAGE;                
                }else if(role.equals("Admin")){
                    session.setAttribute("ADMIN", role);
                    url = ADMIN_PAGE;
                }
            }else{
                request.setAttribute("INVALID", "Invalid email or password !");
            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException at LoginServlet: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at LoginServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at LoginServlet: "+ex.getMessage());
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
