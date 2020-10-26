package nix.servlet;

import nix.bean.Patient;
import nix.bean.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/edit_patient")
public class EditPatientServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EditPatientServlet.class);

    private PatientDao patientDao;

    public void init() {
        patientDao = new PatientDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in EditPatientServlet");
        Patient patient = null;
        boolean validFirstname = true;
        boolean validLastname = true;
        boolean validPhonenumber = true;
        boolean validEmail = true;
        Long patientId = (long) DateUtil.stringToInt(request.getParameter("id").trim());
        System.out.println("GetAttribute id = "+patientId);
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
        String address = request.getParameter("address").trim();


        if( lastName.equals("") || phoneNumber.equals("") ||
                !validFirstname || !validLastname || !validPhonenumber ||!validEmail){
            //если что-то не ввел

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

            request.getRequestDispatcher(Links.PATIENT_LIST).forward(request, response);
        } else{// все ввел
            patient = new Patient();
            patient.setId(patientId);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setGender(gender);
            patient.setAge(age);
            patient.setBirthDate(birthdate);
            patient.setPhoneNumber(phoneNumber);
            patient.setEmail(email);
            patient.setMaritalStatus(maritalStatus);
            patient.setCity(city);
            patient.setAddress(address);

            boolean updated = patientDao.update(patient);

            //всё верно, открываю список докторов
            if(updated)
                request.getSession().setAttribute("successUpdate", "Patient was successfully updated! ");
            else
                request.getSession().setAttribute("failureUpdate", "Patient was not updated! ");

            response.sendRedirect(Links.PATIENT_LIST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execute doGet method in EditPatientServlet");

        Long id = (long) DateUtil.stringToInt(request.getParameter("id"));
        Patient patient = patientDao.findById(id);
        HttpSession session = request.getSession();
        session.setAttribute("id", patient.getId());
        session.setAttribute("firstname", patient.getFirstName());
        session.setAttribute("lastname", patient.getLastName());
        session.setAttribute("gender", patient.getGender());
        session.setAttribute("age", patient.getAge());
        session.setAttribute("birthdate", patient.getBirthDate());
        session.setAttribute("phonenumber", patient.getPhoneNumber());
        session.setAttribute("email", patient.getEmail());
        session.setAttribute("maritalStatus", patient.getMaritalStatus());
        session.setAttribute("city", patient.getCity());
        session.setAttribute("address", patient.getAddress());

        response.sendRedirect(Links.PATIENT_EDIT_JSP);

    }
}
