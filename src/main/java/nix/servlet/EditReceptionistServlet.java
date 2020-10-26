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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(urlPatterns = "/edit_receptionist")
public class EditReceptionistServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(EditReceptionistServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in EditReceptionistServlet");

        System.out.println("IN doPost of EditReceptionistServlet.java");
        User receptionist = null;
        boolean validFirstname = true;
        boolean validLastname = true;
        boolean validPhonenumber = true;
        boolean validEmail = true;
        Long receptionistId = (long) DateUtil.stringToInt(request.getParameter("id").trim());
        System.out.println("GetAttribute id = "+receptionistId);
        String username = request.getParameter("username").trim();
        String firstName = request.getParameter("firstname").trim();
        String lastName = request.getParameter("lastname").trim();
        if(!firstName.equals(""))
            validFirstname = DataValidator.isName(firstName);
        if(!lastName.equals(""))
            validLastname = DataValidator.isName(lastName);
        String gender = request.getParameter("gender").trim();
        int age = DateUtil.stringToInt(request.getParameter("age").trim());
        String birthdate = request.getParameter("birthdate").trim();
        String phoneNumber = request.getParameter("phonenumber").trim();
        if(!phoneNumber.equals(""))
            validPhonenumber = DataValidator.isPhoneNumber(phoneNumber);
        String email = request.getParameter("email").trim();
        if(!email.equals(""))
            validEmail = DataValidator.isEmail(email);
        String maritalStatus = request.getParameter("maritalStatus").trim();
        String city = request.getParameter("city").trim();
        String specification = request.getParameter("specification").trim();
        String qualification = request.getParameter("qualification").trim();
        String hiringDate = request.getParameter("hiringdate").trim();

        if(username.equals("") || lastName.equals("") || phoneNumber.equals("") ||
                !validFirstname || !validLastname || !validPhonenumber ||!validEmail){
            //если что-то не ввел
            if(username.equals(""))
                request.setAttribute("falseUsername", "invalid username");
            if(lastName.equals(""))
                request.setAttribute("falseLastname", "invalid last name");
            if(phoneNumber.equals(""))
                request.setAttribute("falsePhoneNumber", "invalid phone number");
            if(!validFirstname)
                request.setAttribute("falseFirstname", "invalid first name");
            if(!validLastname)
                request.setAttribute("falseLastname", "invalid last lame");
            if(!validPhonenumber)
                request.setAttribute("falsePhoneNumber", "invalid phone number");
            if(!validEmail)
                request.setAttribute("falseEmail", "invalid email");

            request.getRequestDispatcher(Links.RECEPTIONIST_LIST).forward(request, response);
        } else{// все ввел
            receptionist = new User();
            receptionist.setId(receptionistId);
            receptionist.setLogin(username);
            receptionist.setFirstName(firstName);
            receptionist.setLastName(lastName);
            receptionist.setGender(gender);
            receptionist.setAge(age);
            receptionist.setBirthDate(birthdate);
            receptionist.setPhoneNumber(phoneNumber);
            receptionist.setEmail(email);
            receptionist.setMaritalStatus(maritalStatus);
            receptionist.setCity(city);
            receptionist.setSpecialization(specification);
            receptionist.setQualification(qualification);
            receptionist.setHiringDate(hiringDate);
            boolean updated = userDao.update(receptionist);

            //всё верно, открываю список докторов
            if(updated)
                request.getSession().setAttribute("successUpdate", "Receptionist was successfully updated! ");
            else
                request.getSession().setAttribute("failureUpdate", "Receptionist was not updated! ");

            response.sendRedirect(Links.RECEPTIONIST_LIST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execute doGet method in EditReceptionistServlet");

        Long id = (long) DateUtil.stringToInt(request.getParameter("id"));
        User receptionist = new User();
        receptionist = userDao.findById(id);
        HttpSession session = request.getSession();
        session.setAttribute("id", receptionist.getId());
        System.out.println("SetAttribute id = "+id);
        session.setAttribute("username", receptionist.getLogin());
        session.setAttribute("firstname", receptionist.getFirstName());
        session.setAttribute("lastname", receptionist.getLastName());
        session.setAttribute("gender", receptionist.getGender());
        session.setAttribute("age", receptionist.getAge());
        session.setAttribute("birthdate", receptionist.getBirthDate());
        session.setAttribute("phonenumber", receptionist.getPhoneNumber());
        session.setAttribute("maritalStatus", receptionist.getMaritalStatus());
        session.setAttribute("city", receptionist.getCity());
        session.setAttribute("specification", receptionist.getSpecialization());
        session.setAttribute("qualification", receptionist.getQualification());
        session.setAttribute("hiringdate", receptionist.getHiringDate());

        response.sendRedirect(Links.RECEPTIONIST_EDIT_JSP);

    }
}
