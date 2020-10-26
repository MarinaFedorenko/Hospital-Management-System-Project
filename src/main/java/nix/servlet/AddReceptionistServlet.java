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
import java.sql.Date;
import java.sql.Timestamp;

@WebServlet(urlPatterns = "/add_receptionist")
public class AddReceptionistServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(AddReceptionistServlet.class);

    private UserDao receptionistDao;

    public void init() {
        receptionistDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in AddReceptionistServlet");

        User user = null;
        Long userId = null;
        boolean validFirstname = true;
        boolean validLastname = true;
        boolean validPhonenumber = true;
        boolean validEmail = true;
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String firstName = request.getParameter("firstname").trim();
        if(!firstName.equals(""))
            validFirstname = DataValidator.isName(firstName);
        String lastName = request.getParameter("lastname").trim();
        if(!lastName.equals(""))
            validLastname = DataValidator.isName(lastName);
        String gender = request.getParameter("gender").trim();
        int age = DateUtil.stringToInt(request.getParameter("age").trim());
        String birthdate =request.getParameter("birthdate").trim();
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
        int roleId = 2; String createdBy = "admin";
        String modifiedBy = "admin";
        Timestamp createdDatetime = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDatetime = new Timestamp(System.currentTimeMillis());

        if(username.equals("") || password.equals("") || lastName.equals("") || phoneNumber.equals("") ||
                !validFirstname || !validLastname || !validPhonenumber ||!validEmail){
            //если что-то не ввел
            if(username.equals(""))
                request.setAttribute("falseUsername", "invalid username");
            if(password.equals(""))
                request.setAttribute("falsePassword", "invalid password");
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

            request.getRequestDispatcher(Links.RECEPTIONIST_JSP).forward(request, response);

        } else{// все ввел
            System.out.println("Всё верно, сет идёт в бд");
            user = new User(userId, username,password,firstName,lastName,gender,age,
                    birthdate, phoneNumber,email, maritalStatus,city,specification,
                    qualification,hiringDate, (long) roleId,createdBy,modifiedBy,
                    createdDatetime,modifiedDatetime);
            receptionistDao.insert(user);
            request.getSession().setAttribute("successInsert", "Receptionist was successfully added! ");
            //всё верно, открываю список докторов
            response.sendRedirect(Links.RECEPTIONIST_LIST_JSP);

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in AddReceptionistServlet");
        request.getRequestDispatcher(Links.RECEPTIONIST_JSP).forward(request, response);

    }
}
