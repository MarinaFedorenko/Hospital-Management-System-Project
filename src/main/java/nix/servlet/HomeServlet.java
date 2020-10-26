package nix.servlet;

import nix.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns =  "/home")
public class HomeServlet extends HttpServlet{

    private static final Logger logger = LoggerFactory.getLogger(HomeServlet.class);
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Execute doPost method in HomeServlet");
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Execute doGet method in HomeServlet");
        request.getRequestDispatcher(Links.HOME_JSP).forward(request, response);

    }
}
