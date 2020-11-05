package nix.servlet;

import nix.bean.User;
import nix.dao.UserDao;
import nix.util.DataValidator;
import nix.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/receptionist_list")
public class ReceptionistListServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ReceptionistListServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    private void setPage(List<User> receptionists, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("receptionists", receptionists);
        if(receptionists.isEmpty())
            request.setAttribute("falseReceptionist", "no such receptionist");
        request.getRequestDispatcher(Links.RECEPTIONIST_LIST_JSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in ReceptionistServlet");

        List<User> receptionists = new ArrayList<>();
        if(request.getParameter("search")!=null){
            cleanPrompts(request);
            String lastName = request.getParameter("lastname").trim();
            String phoneNumber = request.getParameter("phonenumber").trim();
            boolean validLastName = true;
            boolean validPhoneNumber = true;
            if(!lastName.equals("") || !phoneNumber.equals("")){// ввел или фамилию или номер телефона
                if(!lastName.equals("")&& !phoneNumber.equals("")){ // ввёл и фамилию и телефон
                    validLastName = DataValidator.isName(lastName);
                    validPhoneNumber = DataValidator.isPhoneNumber(phoneNumber);
                    if(validLastName && validPhoneNumber){// и фамилия и телефон валидны
                        receptionists = userDao.findAllByLastnamePhonenumberRoleId(lastName,phoneNumber,(long)3);
                        setPage(receptionists, request, response);
                        return;
                    } else if(validLastName){ // только фамилия валидна
                        receptionists = userDao.findAllByLastnameRoleId(lastName,(long)2);
                        setPage(receptionists, request, response);
                        return;
                    } else if (validPhoneNumber){// только номер телефона валиден
                        receptionists = userDao.findAllByPhonenumberRoleId(phoneNumber,(long)2);
                        setPage(receptionists, request, response);
                        return;
                    } else{// ни фамилия ни телефон не валидны
                        request.setAttribute("falseLastname", "invalid lastname");
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(receptionists, request, response);
                        return;
                    }
                }else if(!lastName.equals("")){// ввел только фамилию
                    validLastName = DataValidator.isName(lastName);
                    if (validLastName){
                        receptionists = userDao.findAllByLastnameRoleId(lastName,(long)2);
                        setPage(receptionists, request, response);
                        return;
                    } else {
                        request.setAttribute("falseLastname", "invalid lastname");
                        setPage(receptionists, request, response);
                        return;
                    }
                } else {
                    validPhoneNumber = DataValidator.isPhoneNumber(phoneNumber);
                    if (validPhoneNumber){
                        receptionists = userDao.findAllByPhonenumberRoleId(phoneNumber,(long)2);
                        setPage(receptionists, request, response);
                        return;
                    } else {
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(receptionists, request, response);
                        return;
                    }
                }
            } else{
                request.getRequestDispatcher(Links.RECEPTIONIST_LIST_JSP).forward(request, response);
                return;
            }

        }else if(request.getParameter("findAll")!=null){
            receptionists = userDao.findAllByRole((long)2);
            cleanPrompts(request);
            setPage(receptionists, request, response);
            return;
        }
        else if (request.getParameter("new")!=null){
            cleanPrompts(request);
            request.getRequestDispatcher(Links.RECEPTIONIST_JSP).forward(request, response);
            return;
        }
        else if(request.getParameter("delete")!=null){}{
            cleanPrompts(request);
            String[] ids = request.getParameterValues("receptionistIds");
            boolean deleted = true;
            if (ids != null && ids.length > 0) {
                User deleteUser = new User();
                for (String id : ids) {
                    deleteUser.setId((long) DateUtil.stringToInt(id));
                    deleted = userDao.delete(deleteUser);
                    if(deleted)
                        request.getSession().setAttribute("successDelete", "Receptionist was successfully deleted! ");

                }
            }
            receptionists = userDao.findAllByRole((long)2);
            setPage(receptionists, request, response);



        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in ReceptionistServlet");
        List<User> receptionists ;
        receptionists = userDao.findAllByRole((long)2);
        request.setAttribute("receptionists", receptionists);
        cleanPrompts(request);
        request.getRequestDispatcher(Links.RECEPTIONIST_LIST_JSP).forward(request, response);
    }

    private void cleanPrompts(HttpServletRequest request){
        request.setAttribute("successUpdate","");
        request.setAttribute("successInsert","");
        request.setAttribute("successDelete","");
        request.setAttribute("failureUpdate","");
        request.setAttribute("failureInsert","");
        request.setAttribute("failureDelete","");
    }
}
