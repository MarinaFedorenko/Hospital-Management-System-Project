package nix.servlet;

import com.mysql.cj.Session;
import nix.bean.User;
import nix.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Execute doPost method in LoginServlet");

        String username = request.getParameter("MainUserUsername").trim();
        String password = request.getParameter("MainUserPassword").trim();


        if(username.equals("") || password.equals("")) {  // что-то не ввёл
            if (username.equals(""))
                request.setAttribute("falseUsername", "invalid username");
            if (password.equals(""))
                request.setAttribute("falsePassword", "invalid password");

            request.getRequestDispatcher(Links.LOGIN_JSP).forward(request, response);
            return;
        }
        else{ //всё ввёл, иду в бд
            User user = userDao.login(username, password);
            if (user != null) { //всё верно, открываю доступ
                MyProfileServlet.setMainUserLogin(username);//сохраняю логин
                MyProfileServlet.setMainUserPassword(password);//сохраняю пароль
                String role;
                HttpSession session = request.getSession();

                if(user.getRoleId()==1)
                    role = "Admin";
                else if(user.getRoleId()==2)
                    role = "Receptionist";
                else
                    role = "Doctor";

                session.setAttribute("user", user);
                session.setAttribute("firstname", user.getFirstName());
                session.setAttribute("lastname", user.getLastName());
                session.setAttribute("role", role);


                response.sendRedirect(Links.HOME_JSP);

            }
            else {
                request.setAttribute("falseUser", "no such user");
                request.getRequestDispatcher(Links.LOGIN_JSP).forward(request, response);

            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in LoginServlet");
        request.getRequestDispatcher(Links.LOGIN_JSP).forward(request, response);


    }
}
