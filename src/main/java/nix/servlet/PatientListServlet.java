package nix.servlet;

import nix.bean.Patient;
import nix.dao.PatientDao;

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
import java.util.List;

@WebServlet(urlPatterns = "/patient_list")
public class PatientListServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(PatientListServlet.class);

    private PatientDao patientDao;

    public void init() {
        patientDao = new PatientDao();
    }

    private void setPage(List<Patient> patients, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("patients", patients);
        if(patients.isEmpty())
            request.setAttribute("falsePatient", "no such patient");
        request.getRequestDispatcher(Links.PATIENT_LIST_JSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in PatientListServlet");

        List<Patient> patients = new ArrayList<>();
        if(request.getParameter("search")!=null){
            cleanPrompts(request);
            String lastName = request.getParameter("lastname").trim();
            String phoneNumber = request.getParameter("phonenumber").trim();
            boolean validLastName = true;
            boolean validPhoneNumber = true;
            if(!lastName.equals("") || !phoneNumber.equals("")){// ввел или фамилию или номер телефона
                if(!lastName.equals("")&& !phoneNumber.equals("")){ // ввёл и фамилию и телефон
                    validLastName = DataValidator.isName(lastName);
                    validPhoneNumber = DataValidator.isPhoneNumber(phoneNumber);
                    if(validLastName && validPhoneNumber){// и фамилия и телефон валидны
                        patients = patientDao.findAllByLastnamePhonenumber(lastName,phoneNumber);
                        setPage(patients, request, response);
                        return;
                    } else if(validLastName){ // только фамилия валидна
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        patients = patientDao.findAllByLastname(lastName);
                        setPage(patients, request, response);
                        return;
                    } else if (validPhoneNumber){// только номер телефона валиден
                        request.setAttribute("falseLastname", "invalid lastname");
                        patients = patientDao.findAllByPhonenumber(phoneNumber);
                        setPage(patients, request, response);
                        return;
                    } else{// ни фамилия ни телефон не валидны
                        cleanPrompts(request);
                        request.setAttribute("falseLastname", "invalid lastname");
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(patients, request, response);
                        return;
                    }
                }else if(!lastName.equals("")){// ввел только фамилию
                    validLastName = DataValidator.isName(lastName);
                    if (validLastName){
                        cleanPrompts(request);
                        patients = patientDao.findAllByLastname(lastName);
                        setPage(patients, request, response);
                        return;
                    } else {
                        request.setAttribute("falseLastname", "invalid lastname");
                        setPage(patients, request, response);
                        return;
                    }
                } else {
                    validPhoneNumber = DataValidator.isPhoneNumber(phoneNumber);
                    if (validPhoneNumber){
                        cleanPrompts(request);
                        patients = patientDao.findAllByPhonenumber(phoneNumber);
                        setPage(patients, request, response);
                        return;
                    } else {
                        request.setAttribute("falsePhonenumber", "invalid phone number");
                        setPage(patients, request, response);
                        return;
                    }
                }
            } else{
                request.getRequestDispatcher(Links.PATIENT_LIST_JSP).forward(request, response);
                return;
            }

        }
        else if(request.getParameter("findAll")!=null){
            patients = patientDao.findAll();
            cleanPrompts(request);
            setPage(patients, request, response);
            return;
        }
        else if (request.getParameter("new")!=null){
            cleanPrompts(request);
            request.getRequestDispatcher(Links.PATIENT_JSP).forward(request, response);
            return;
        }
        else if(request.getParameter("delete")!=null){}{
            cleanPrompts(request);
            String[] ids = request.getParameterValues("patientIds");
            boolean deleted = true;
            if (ids != null && ids.length > 0) {
                Patient deletePatient = new Patient();
                for (String id : ids) {
                    deletePatient.setId((long) DateUtil.stringToInt(id));
                    deleted = patientDao.delete(deletePatient);
                    if(deleted)
                        request.getSession().setAttribute("successDelete", "Patient was successfully deleted! ");

                }
            }
            patients = patientDao.findAll();
            setPage(patients, request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in PatientListServlet");

        List<Patient> patients ;
        patients = patientDao.findAll();
        request.setAttribute("patients", patients);
        cleanPrompts(request);
        request.getRequestDispatcher(Links.PATIENT_LIST_JSP).forward(request, response);
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
