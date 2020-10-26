package nix.servlet;

import nix.bean.Appointment;
import nix.dao.AppointmentDao;
import nix.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(urlPatterns = "/add_appointment")
public class AddAppointmentServlet extends HttpServlet implements Links {
    private static final Logger logger = LoggerFactory.getLogger(AddAppointmentServlet.class);
    private AppointmentDao appointmentDao;

    public void init() {
        appointmentDao = new AppointmentDao();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in AddAppointmentServlet");

        Appointment appointment = null;
        Long appointmentId = null;
        Long doctorId = (long)DateUtil.stringToInt(request.getParameter("doctor").trim());
        Long patientId = (long)DateUtil.stringToInt(request.getParameter("patient").trim());
        String time = request.getParameter("time").trim();
        String date = request.getParameter("date").trim();
        String createdBy = "admin";
        String modifiedBy = "admin";
        Timestamp createdDatetime = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDatetime = new Timestamp(System.currentTimeMillis());

        if(doctorId==0 || patientId==0 || time.equals("") || date.equals("")){
            //если что-то не ввел
            if(time.equals(""))
                request.setAttribute("falseTime", "invalid time");
            if(date.equals(""))
                request.setAttribute("falseDate", "invalid date");


            request.getRequestDispatcher(Links.APPOINTMENT_JSP).forward(request, response);

        } else{// все ввел
            System.out.println("Всё верно, сет идёт в бд");
            appointment = new Appointment(appointmentId, doctorId,patientId,time,date, createdBy,
                    modifiedBy, createdDatetime, modifiedDatetime);
            appointmentDao.insert(appointment);
//            request.getSession().setAttribute("successInsert", "Appointment was successfully added! ");
            //всё верно, открываю список докторов
            response.sendRedirect(Links.APPOINTMENT_LIST_JSP);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in AddAppointmentServlet");
        request.getRequestDispatcher(Links.APPOINTMENT_JSP).forward(request, response);

    }
}
