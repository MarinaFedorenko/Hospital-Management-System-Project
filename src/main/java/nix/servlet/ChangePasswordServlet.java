package nix.servlet;

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

@WebServlet(urlPatterns = "/change_password")
public class ChangePasswordServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordServlet.class);

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in ChangePasswordServlet");
        String username = MyProfileServlet.getMainUserLogin();
        String oldPassword = request.getParameter("currentPassword").trim();
        String newPassword = request.getParameter("newPassword").trim();
        if(oldPassword.equals("") || newPassword.equals("")) {  // что-то не ввёл
                request.setAttribute("falsePassword", "invalid password");
            request.getRequestDispatcher(Links.CHANGE_PASSWORD_JSP).forward(request, response);
            return;
        }
        else{ //всё ввёл, иду в бд
            userDao.changePassword(username, newPassword);
            response.sendRedirect(Links.HOME_JSP);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in ChangePasswordServlet");
        request.getRequestDispatcher(Links.CHANGE_PASSWORD_JSP).forward(request, response);
    }
}
