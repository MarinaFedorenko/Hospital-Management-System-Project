package nix.servlet;


import nix.bean.Appointment;
import nix.bean.Patient;
import nix.bean.User;
import nix.dao.AppointmentDao;
import nix.dao.PatientDao;
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
import java.sql.Timestamp;

@WebServlet(urlPatterns = "/edit_appointment")
public class EditAppointmentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EditAppointmentServlet.class);

    private AppointmentDao appointmentDao;

    public void init() {
        appointmentDao = new AppointmentDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in EditAppointmentServlet");
        Appointment appointment = null;

        Long appointmentId = (long) DateUtil.stringToInt(request.getParameter("id").trim());
        Long doctorId = (long)DateUtil.stringToInt(request.getParameter("doctorId").trim());
        Long patientId = (long)DateUtil.stringToInt(request.getParameter("patientId").trim());
        String time = request.getParameter("time").trim();
        String date = request.getParameter("date").trim();
        String modifiedBy = request.getParameter("modifiedBy").trim();
        Timestamp modifiedDatetime = new Timestamp(System.currentTimeMillis());

        if( time.equals("") || date.equals("")){
            //если что-то не ввел
            if(time.equals(""))
                request.setAttribute("falseTime", "invalid time");
            if(date.equals(""))
                request.setAttribute("falseDate", "invalid date");

            request.getRequestDispatcher(Links.APPOINTMENT_LIST).forward(request, response);
        } else{// все ввел
            appointment = new Appointment();
            appointment.setId(appointmentId);
            appointment.setDoctorId(doctorId);
            appointment.setPatientId(patientId);
            appointment.setTime(time);
            appointment.setDate(date);
            appointment.setModifiedBy(modifiedBy);
            appointment.setModifiedDatetime(modifiedDatetime);

            boolean updated = appointmentDao.update(appointment);

            //всё верно, открываю список докторов
            if(updated)
                request.getSession().setAttribute("successUpdate", "Appointment was successfully updated! ");
            else
                request.getSession().setAttribute("failureUpdate", "Appointment was not updated! ");

            response.sendRedirect(Links.APPOINTMENT_LIST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execute doGet method in EditAppointmentServlet");

        Long id = (long) DateUtil.stringToInt(request.getParameter("id"));
        Appointment appointment = new Appointment();
        appointment = appointmentDao.findById(id);
        UserDao doctorDao = new UserDao();
        PatientDao patientDao = new PatientDao();
        User doctor = doctorDao.findById(appointment.getDoctorId());
        Patient patient = patientDao.findById(appointment.getPatientId());
        String doctorName = doctor.getFirstName()+"  "+doctor.getLastName();
        String patientName = patient.getFirstName()+"  "+patient.getLastName();

        HttpSession session = request.getSession();
        session.setAttribute("id", appointment.getId());
        session.setAttribute("doctor", doctorName);
        session.setAttribute("doctorId", appointment.getDoctorId());
        session.setAttribute("patient",patientName);
        session.setAttribute("patientId", appointment.getPatientId());
        session.setAttribute("time", appointment.getTime());
        session.setAttribute("date", appointment.getDate());
        session.setAttribute("modifiedBy", appointment.getModifiedBy());

        response.sendRedirect(Links.APPOINTMENT_EDIT_JSP);

    }
}
