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
import tinnt.user.UserCreateError;
import tinnt.user.UserDAO;
import tinnt.utils.MyUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateNewAccountServlet.class);
    
    private final String CREATE_ACCOUNT_PAGE = "createNewAccount.jsp";
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
        
        String url = CREATE_ACCOUNT_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        
        String emailFormat = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        
        UserCreateError errs = new UserCreateError();
        boolean foundErr = false;
        
        try {
            //check empty
            if(email.trim().length() == 0 || password.trim().length() == 0
                    || name.trim().length() == 0 || confirm.trim().length() == 0){
                foundErr = true;
                errs.setEmptyInputErr("Inputs are required");
            }else{
                //check email
                if(email.trim().length() < 6 || email.trim().length() > 50){
                    foundErr = true;
                    errs.setEmailLengthErr("Email requires from 6 to 50 chars");
                }else if(!email.matches(emailFormat)) {
                    foundErr = true;
                    errs.setEmailWrongFormatErr("Invalid Email");
                }
                
                //check password
                if(password.trim().length() < 6 || password.trim().length() > 30){
                    foundErr = true;
                    errs.setPasswordLengthErr("Password requires from 6 to 30 chars");
                }else if(!confirm.trim().equals(password.trim())) {
                    foundErr = true;
                    errs.setConfirmNotMatched("Confirm not match password !");
                }
                
                //check name
                if(name.trim().length() < 2 || name.trim().length() > 50){
                    foundErr = true;
                    errs.setFullNameLengthErr("Full name requires from 2 to 50 chars");
                }
            }
            
            if(foundErr == true){
                request.setAttribute("CREATE_ERR", errs);
            }else{
                UserDAO uDAO = new UserDAO();
                String enPass = MyUtils.encryptPassword(password);
                boolean result = uDAO.createAccount(email, enPass, name);
                if(result == true){
                    request.setAttribute("CREATE_SUCCESS", "Register successful !");
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.error("SQLException at CreateNewAccountServlet: "+msg);
            if (msg.contains("duplicate")) {
                errs.setEmailIsExisted("Email is existed!!!");
                request.setAttribute("CREATE_ERR", errs);
            }//end if msg
        } catch (ClassNotFoundException ex) {
            LOGGER.error("ClassNotFoundException at CreateNewAccountServlet: "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("NamingException at CreateNewAccountServlet: "+ex.getMessage());
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
