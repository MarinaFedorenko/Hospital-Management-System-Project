package nix.servlet;

import nix.bean.User;
import nix.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/my_profile")
public class MyProfileServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(MyProfileServlet.class);
    private static String mainUserLogin;
    private static String mainUserPassword;

    public MyProfileServlet(){}


    private UserDao userDao;


    public void init() {
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        logger.info("Execute doPost method in ProfileServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execute doGet method in ProfileServlet");
        String username = MyProfileServlet.getMainUserLogin();
        String password = MyProfileServlet.getMainUserPassword();
        UserDao userDao = new UserDao();
        User me = userDao.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("firstname", me.getFirstName());
        session.setAttribute("lastname", me.getLastName());
        session.setAttribute("gender", me.getGender());
        session.setAttribute("age", me.getAge());
        session.setAttribute("birthdate", me.getBirthDate());
        session.setAttribute("phonenumber", me.getPhoneNumber());
        session.setAttribute("email", me.getEmail());
        session.setAttribute("maritalStatus", me.getMaritalStatus());
        session.setAttribute("city", me.getCity());
        session.setAttribute("specification", me.getSpecialization());
        session.setAttribute("qualification", me.getQualification());
        session.setAttribute("hiringDate", me.getHiringDate());
        session.setAttribute("createdBy", me.getCreatedBy());
        session.setAttribute("modifiedBy", me.getModifiedBy());
        session.setAttribute("createdDate", me.getCreatedDatetime());
        session.setAttribute("modifiedDate", me.getModifiedDatetime());
        response.sendRedirect(Links.MY_PROFILE_JSP);


    }

    public static String getMainUserLogin() {
        return mainUserLogin;
    }

    public static void setMainUserLogin(String mainUserLogin) {
        MyProfileServlet.mainUserLogin = mainUserLogin;
    }

    public static String getMainUserPassword() {
        return mainUserPassword;
    }

    public static void setMainUserPassword(String mainUserPassword) {
        MyProfileServlet.mainUserPassword = mainUserPassword;
    }
}
