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


@WebServlet(urlPatterns = "/edit_doctor")
public class EditDoctorServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EditDoctorServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in EditDoctorServlet");

        System.out.println("IN doPost of EditDoctorServlet.java");
        User doctor = null;
        boolean validFirstname = true;
        boolean validLastname = true;
        boolean validPhonenumber = true;
        boolean validEmail = true;
        Long doctorId = (long)DateUtil.stringToInt(request.getParameter("id").trim());
        System.out.println("GetAttribute id = "+doctorId);
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

            request.getRequestDispatcher(Links.DOCTOR_LIST).forward(request, response);
        } else{// все ввел
            doctor = new User();
            doctor.setId(doctorId);
            doctor.setLogin(username);
            doctor.setFirstName(firstName);
            doctor.setLastName(lastName);
            doctor.setGender(gender);
            doctor.setAge(age);
            doctor.setBirthDate(birthdate);
            doctor.setPhoneNumber(phoneNumber);
            doctor.setEmail(email);
            doctor.setMaritalStatus(maritalStatus);
            doctor.setCity(city);
            doctor.setSpecialization(specification);
            doctor.setQualification(qualification);
            doctor.setHiringDate(hiringDate);
            boolean updated = userDao.update(doctor);

            //всё верно, открываю список докторов
            if(updated)
                request.getSession().setAttribute("successUpdate", "Doctor was successfully updated! ");
            else
                request.getSession().setAttribute("failureUpdate", "Doctor was not updated! ");

            response.sendRedirect(Links.DOCTOR_LIST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execute doGet method in EditDoctorServlet");

        Long id = (long) DateUtil.stringToInt(request.getParameter("id"));
        User doctor = new User();
        doctor = userDao.findById(id);
        HttpSession session = request.getSession();
        session.setAttribute("id", doctor.getId());
        System.out.println("SetAttribute id = "+id);
        session.setAttribute("username", doctor.getLogin());
        session.setAttribute("firstname", doctor.getFirstName());
        session.setAttribute("lastname", doctor.getLastName());
        session.setAttribute("gender", doctor.getGender());
        session.setAttribute("age", doctor.getAge());
        session.setAttribute("birthdate", doctor.getBirthDate());
        session.setAttribute("phonenumber", doctor.getPhoneNumber());
        session.setAttribute("maritalStatus", doctor.getMaritalStatus());
        session.setAttribute("city", doctor.getCity());
        session.setAttribute("specification", doctor.getSpecialization());
        session.setAttribute("qualification", doctor.getQualification());
        session.setAttribute("hiringdate", doctor.getHiringDate());

        response.sendRedirect(Links.DOCTOR_EDIT_JSP);

    }
}
