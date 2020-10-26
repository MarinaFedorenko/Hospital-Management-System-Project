package nix.servlet;

public interface Links {

    public String APP_CONTEXT = "/HospitalManagementSys";

//    public String PAGE_FOLDER = "/jsp";

//    public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";


    public String ADD_DOCTOR = "/add_doctor";
    public String DOCTOR_LIST = "/doctor_list";

    public String ADD_PATIENT = "/add_patient";
    public String PATIENT_LIST = "/patient_list";


    public String ADD_RECEPTIONIST = "/add_receptionist";
    public String RECEPTIONIST_LIST = "/receptionist_list";


    public String ADD_APPOINTMENT = "/add_appointment";
    public String APPOINTMENT_LIST =  "/appointment_list";


    public String LOGIN = "/login";
    public String LOGOUT = "/logout";
    public String HOME = "/home";
    public String CHANGE_PASSWORD = "/change_password";
    public String MY_PROFILE =   "/my_profile";



    public String PATIENT_JSP =   "addPatient.jsp";
    public String PATIENT_LIST_JSP = "patientList.jsp";
    public String PATIENT_EDIT_JSP =  "editPatient.jsp";


    public String APPOINTMENT_JSP =  "addAppointment.jsp";
    public String APPOINTMENT_LIST_JSP =  "appointmentList.jsp";
    public String APPOINTMENT_EDIT_JSP =  "editAppointment.jsp";


    public String DOCTOR_JSP =  "addDoctor.jsp";
    public String DOCTOR_LIST_JSP =  "doctorList.jsp";
    public String DOCTOR_EDIT_JSP =  "editDoctor.jsp";


    public String RECEPTIONIST_JSP = "addReceptionist.jsp";
    public String RECEPTIONIST_LIST_JSP = "receptionistList.jsp";
    public String RECEPTIONIST_EDIT_JSP =  "editReceptionist.jsp";


    public String LOGIN_JSP =  "login.jsp";
    public String HOME_JSP =  "home.jsp";
    public String CHANGE_PASSWORD_JSP =  "changePassword.jsp";
    public String MY_PROFILE_JSP = "myProfile.jsp";
    public String FORGET_PASSWORD_JSP = "forgetPassword.jsp";
}
