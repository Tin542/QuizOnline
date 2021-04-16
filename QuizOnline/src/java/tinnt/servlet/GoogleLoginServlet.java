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
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinnt.subject.SubjectDAO;
import tinnt.subject.SubjectDTO;
import tinnt.user.GoogleDTO;
import tinnt.user.UserDAO;
import tinnt.user.UserDTO;
import tinnt.utils.GoogleUtil;
import tinnt.utils.MyUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/GoogleLoginServlet"})
public class GoogleLoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GoogleLoginServlet.class);
    
    private final String LOGIN_PAGE = "login.jsp";
    private final String STUDENT_PAGE = "student.jsp";
    private final String DEFAULT_PASSWORD = "123456";
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
        
        String code = request.getParameter("code");
        
        String url = LOGIN_PAGE;
        
        try {
            if(code == null || code.isEmpty()){
                
            }else{
                String accessToken = GoogleUtil.getToken(code);
                GoogleDTO googlePojo = GoogleUtil.getUserInfo(accessToken);
                String name = null;
                String password = MyUtils.encryptPassword(DEFAULT_PASSWORD);
                
                if(googlePojo.getName() == null){
                    name = googlePojo.getEmail().split("@")[0];
                }
                
                HttpSession session = request.getSession();  
                
                UserDAO uDAO = new UserDAO();
                UserDTO uDTO = uDAO.checkLogin(googlePojo.getEmail(), password);
                if(uDTO == null){
                    uDAO.createAccount(googlePojo.getEmail(), password, name);
                }
                session.setAttribute("FULLNAME", name);
                session.setAttribute("EMAIL", googlePojo.getEmail());
                session.setAttribute("STUDENT", "Student");
                SubjectDAO sDAO = new SubjectDAO();
                sDAO.getAllSubject();
                List<SubjectDTO> listSubject = sDAO.getListSubject();
                session.setAttribute("listsub", listSubject);

                url = STUDENT_PAGE;
                
            }
        }catch(IOException e){
            LOGGER.error("IOException at GoogleLoginServlet: "+e);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at GoogleLoginServlet: "+ex);
        } catch (NamingException ex) {
            LOGGER.error("NamingException at GoogleLoginServlet: "+ex);
        } catch (SQLException ex) {
            LOGGER.error("SQLException at GoogleLoginServlet: "+ex);
        }finally{
            response.sendRedirect(url);
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
