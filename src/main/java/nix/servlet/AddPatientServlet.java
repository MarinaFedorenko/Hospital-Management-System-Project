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
import java.sql.Date;
import java.sql.Timestamp;



@WebServlet(urlPatterns = "/add_patient")
public class AddPatientServlet extends HttpServlet implements Links{
    private static final Logger logger = LoggerFactory.getLogger(AddPatientServlet.class);

    private PatientDao patientDao;

    public void init() {
        patientDao = new PatientDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doPost method in AddPatientServlet");

        Patient patient = null;
        Long patientId = null;
        boolean validFirstname = true;
        boolean validLastname = true;
        boolean validPhonenumber = true;
        boolean validEmail = true;
        String firstName = request.getParameter("firstname").trim();
        if(!firstName.equals(""))
            validFirstname = DataValidator.isName(firstName);
        String lastName = request.getParameter("lastname").trim();
        if(!lastName.equals(""))
            validLastname = DataValidator.isName(lastName);
        String gender = request.getParameter("gender").trim();
        int age = DateUtil.stringToInt(request.getParameter("age").trim());
//        Date birthdate = DateUtil.stringToDate(request.getParameter("birthdate").trim());
        String birthdate = request.getParameter("birthdate").trim();
        String phoneNumber = request.getParameter("phonenumber").trim();
        if(!phoneNumber.equals(""))
            validPhonenumber = DataValidator.isPhoneNumber(phoneNumber);
        String email = request.getParameter("email").trim();
        if(!email.equals(""))
            validEmail = DataValidator.isEmail(email);
        String maritalStatus = request.getParameter("maritalStatus").trim();
        String city = request.getParameter("city").trim();
        String address = request.getParameter("address");
        String createdBy = "admin";
        String modifiedBy = "admin";
        Timestamp createdDatetime = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDatetime = new Timestamp(System.currentTimeMillis());

        if(lastName.equals("") || phoneNumber.equals("") ||
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

            request.getRequestDispatcher(Links.PATIENT_JSP).forward(request, response);

        } else{// все ввел
            System.out.println("Всё верно, сет идёт в бд");
            patient = new Patient(patientId,firstName,lastName,gender,age,
                    birthdate, phoneNumber,email, maritalStatus,city,address, createdBy,modifiedBy,
                    createdDatetime,modifiedDatetime);
            patientDao.insert(patient);
            request.getSession().setAttribute("successInsert", "Patient was successfully added! ");
            //всё верно, открываю список докторов
            response.sendRedirect(Links.PATIENT_LIST_JSP);

        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execute doGet method in AddPatientServlet");
        request.getRequestDispatcher(Links.PATIENT_JSP).forward(request, response);


    }
}
