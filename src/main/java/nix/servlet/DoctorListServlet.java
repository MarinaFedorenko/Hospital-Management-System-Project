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


@WebServlet(urlPatterns = "/doctor_list")
public class DoctorListServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DoctorListServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    private void setPage(List<User> doctors, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("doctors", doctors);
            if(doctors.isEmpty())
                request.setAttribute("falseDoctor", "no such doctor");
            request.getRequestDispatcher(Links.DOCTOR_LIST_JSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in DoctorListServlet");

        List<User> doctors = new ArrayList<>();
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
                        doctors = userDao.findAllByLastnamePhonenumberRoleId(lastName,phoneNumber,(long)3);
                        setPage(doctors, request, response);
                        return;
                    } else if(validLastName){ // только фамилия валидна
                        doctors = userDao.findAllByLastnameRoleId(lastName,(long)3);
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(doctors, request, response);
                        return;
                    } else if (validPhoneNumber){// только номер телефона валиден
                        doctors = userDao.findAllByPhonenumberRoleId(phoneNumber,(long)3);
                        request.setAttribute("falseLastname", "invalid lastname");
                        setPage(doctors, request, response);
                        return;
                    } else{// ни фамилия ни телефон не валидны
                        request.setAttribute("falseLastname", "invalid lastname");
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(doctors, request, response);
                        return;
                    }
                }else if(!lastName.equals("")){// ввел только фамилию
                    validLastName = DataValidator.isName(lastName);
                    if (validLastName){
                        doctors = userDao.findAllByLastnameRoleId(lastName,(long)3);
                        setPage(doctors, request, response);
                        return;
                    } else {
                        request.setAttribute("falseLastname", "invalid lastname");
                        setPage(doctors, request, response);
                        return;
                    }
                } else {
                    validPhoneNumber = DataValidator.isPhoneNumber(phoneNumber);
                    if (validPhoneNumber){
                        doctors = userDao.findAllByPhonenumberRoleId(phoneNumber,(long)3);
                        setPage(doctors, request, response);
                        return;
                    } else {
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(doctors, request, response);
                        return;
                    }
                }
            } else{
                request.getRequestDispatcher(Links.DOCTOR_LIST_JSP).forward(request, response);
                return;
            }

        }
        else if(request.getParameter("findAll")!=null){
            doctors = userDao.findAllByRole((long)3);
            cleanPrompts(request);
            setPage(doctors, request, response);
            return;
        }
        else if (request.getParameter("new")!=null){
            cleanPrompts(request);
            request.getRequestDispatcher(Links.DOCTOR_JSP).forward(request, response);
            return;
        }
        else if(request.getParameter("delete")!=null){}{
            cleanPrompts(request);
            String[] ids = request.getParameterValues("doctorIds");
            boolean deleted = true;
            if (ids != null && ids.length > 0) {
                User deleteUser = new User();
                for (String id : ids) {
                    deleteUser.setId((long)DateUtil.stringToInt(id));
                    deleted = userDao.delete(deleteUser);
                    if(deleted)
                        request.getSession().setAttribute("successDelete", "Doctor was successfully deleted! ");

                }
            }
            doctors = userDao.findAllByRole((long)3);
                setPage(doctors, request, response);



        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in DoctorListServlet");

        List<User> doctors ;
        doctors = userDao.findAllByRole((long)3);
        request.setAttribute("doctors", doctors);
        cleanPrompts(request);
        request.getRequestDispatcher(Links.DOCTOR_LIST_JSP).forward(request, response);
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
