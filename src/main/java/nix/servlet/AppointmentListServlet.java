package nix.servlet;

import com.mysql.cj.ParseInfo;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/appointment_list")
public class AppointmentListServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(AppointmentListServlet.class);

    private AppointmentDao appointmentDao;

    public void init() {
        appointmentDao = new AppointmentDao();
    }

    private void setPage(List<Appointment> appointments, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("appointments", appointments);
        if(appointments.isEmpty())
            request.setAttribute("falseAppointment", "no such appointment");
        request.getRequestDispatcher(Links.APPOINTMENT_LIST_JSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in AppointmentListServlet");

        List<Appointment> appointments = new ArrayList<>();
        UserDao userDao = new UserDao();
        PatientDao patientDao = new PatientDao();

        if(request.getParameter("search")!=null){
            String doctorLastName = request.getParameter("doctor").trim();
            String patientLastName = request.getParameter("patient").trim();
            boolean validDoctorLastName = true;
            boolean validPatientLastName = true;
            if(!doctorLastName.equals("") || !patientLastName.equals("")){
                if(!doctorLastName.equals("")&& !patientLastName.equals("")){
                    validDoctorLastName = DataValidator.isName(doctorLastName);
                    validPatientLastName = DataValidator.isName(patientLastName);
                    if(validDoctorLastName && validPatientLastName){
                        List<User> doctors = userDao.findAllByLastnameRoleId(doctorLastName, (long)3);
                        List<Patient> patients = patientDao.findAllByLastname(patientLastName);
                        List <Long >doctorIds = new ArrayList(doctors.size());
                        List <Long> patientsIds = new ArrayList(patients.size());

                        List<Appointment> results = null;
                        for(int i=0; i< doctors.size(); i++)
                            doctorIds.add(doctors.get(i).getId());
                        for(int i=0; i< patients.size(); i++)
                            patientsIds.add(patients.get(i).getId());

                        for(int i=0; i<doctorIds.size(); i++){
                            for(int j=0; j<patientsIds.size(); j++){
                               results =  appointmentDao.findAllByDoctorIdPatientId(doctorIds.get(i), patientsIds.get(j));
                                for(int k=0; k<results.size(); k++){
                                    appointments.add(results.get(i));
                                }
                            }
                        }
                        setPage(appointments, request, response);
                        return;
                    } else if(validDoctorLastName){
                        List<User> doctors = userDao.findAllByLastnameRoleId(doctorLastName, (long)3);
                        List <Long >doctorIds = new ArrayList(doctors.size());
                        List<Appointment> results = null;

                        for(int i=0; i< doctors.size(); i++)
                            doctorIds.add(doctors.get(i).getId());
                        for(int i=0; i<doctorIds.size(); i++){
                            results = appointmentDao.findByDoctorId(doctorIds.get(i));
                            for(int k=0; k<results.size(); k++){
                                appointments.add(results.get(i));
                            }
                        }
                        setPage(appointments, request, response);
                        return;
                    } else if (validPatientLastName){
                        List<Patient> patients = patientDao.findAllByLastname(patientLastName);
                        List <Long >patientIds = new ArrayList(patients.size());
                        List<Appointment> results = null;

                        for(int i=0; i< patients.size(); i++)
                            patientIds.add(patients.get(i).getId());
                        for(int i=0; i<patientIds.size(); i++){
                            results = appointmentDao.findByPatientId(patientIds.get(i));
                            for(int k=0; k<results.size(); k++){
                                appointments.add(results.get(i));
                            }
                        }
                        setPage(appointments, request, response);
                        return;
                    } else{
                        request.setAttribute("falseDoctor", "invalid doctor last name");
                        request.setAttribute("falsePatient", "invalid patient last name");
                        setPage(appointments, request, response);
                        return;
                    }
                }else if(!doctorLastName.equals("")){// ввел только фамилию
                    validDoctorLastName = DataValidator.isName(doctorLastName);
                    if (validDoctorLastName){
                        List<User> doctors = userDao.findAllByLastnameRoleId(doctorLastName, (long)3);
                        List <Long >doctorIds = new ArrayList(doctors.size());
                        List<Appointment> results = null;

                        for(int i=0; i< doctors.size(); i++)
                            doctorIds.add(doctors.get(i).getId());
                        for(int i=0; i<doctorIds.size(); i++){
                            results = appointmentDao.findByDoctorId(doctorIds.get(i));
                            for(int k=0; k<results.size(); k++){
                                appointments.add(results.get(i));
                            }
                        }
                        setPage(appointments, request, response);
                        return;
                    } else {
                        request.setAttribute("falseDoctor", "invalid doctor last name");
                        setPage(appointments, request, response);
                        return;
                    }
                } else {
                    validPatientLastName = DataValidator.isName(patientLastName);
                    if (validPatientLastName){
                        List<Patient> patients = patientDao.findAllByLastname(patientLastName);
                        List <Long >patientIds = new ArrayList(patients.size());
                        List<Appointment> results = null;

                        for(int i=0; i< patients.size(); i++)
                            patientIds.add(patients.get(i).getId());
                        for(int i=0; i<patientIds.size(); i++){
                            results = appointmentDao.findByPatientId(patientIds.get(i));
                            for(int k=0; k<results.size(); k++){
                                appointments.add(results.get(i));
                            }
                        }
                        setPage(appointments, request, response);
                        return;
                    } else {
                        request.setAttribute("falsePatient", "invalid patient last name");
                        setPage(appointments, request, response);
                        return;
                    }
                }
            } else{
                request.getRequestDispatcher(Links.APPOINTMENT_LIST_JSP).forward(request, response);
                return;
            }

        }
        else if(request.getParameter("findAll")!=null){

            appointments = appointmentDao.findAll();
            cleanPrompts(request);
            setPage(appointments, request, response);
            return;
        }
        else if (request.getParameter("new")!=null){
            request.getRequestDispatcher(Links.APPOINTMENT_JSP).forward(request, response);
            return;
        }
        else if(request.getParameter("delete")!=null){}{
            String[] ids = request.getParameterValues("appointmentsIds");
            boolean deleted = true;
            if (ids != null && ids.length > 0) {
                Appointment deleteAppointment = new Appointment();
                for (String id : ids) {
                    deleteAppointment.setId((long) DateUtil.stringToInt(id));
                    deleted = appointmentDao.delete(deleteAppointment);
                    if(deleted)
                        request.getSession().setAttribute("successDelete", "Appointment was successfully deleted! ");

                }
            }
            appointments = appointmentDao.findAll();
            setPage(appointments, request, response);

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in AppointmentListServlet");

        List<Appointment> appointments ;
        appointments = appointmentDao.findAll();
        request.setAttribute("appointments", appointments);
        cleanPrompts(request);
        request.getRequestDispatcher(Links.APPOINTMENT_LIST_JSP).forward(request, response);
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
