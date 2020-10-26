<%@page import="nix.servlet.AddAppointmentServlet"%>
<%@page import="java.util.HashMap"%>
<%@ page import="nix.dao.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="nix.dao.PatientDao" %>
<%@ page import="nix.bean.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medical APP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <link rel="stylesheet" type="text/css" href="css/split.css">
    <link rel="stylesheet" type="text/css" href="css/form.css"></head>
<body>
<%@ include file="menu.jsp" %>


<div class="split right" style="align-content: center">
    <form action="<%=request.getContextPath()%> /add_appointment" method="post"  >
        <div class="wrapper">
            <div class="title">
                Add new Appointment
            </div>
            <div class="form">
                <div class="inputfield">
                    <label>Doctor</label>
                    <select name="doctor">

                    <%
                        UserDao userDao = new UserDao();
                        List doctors = userDao.findAllByRole((long)3);
                        Iterator<User> it = doctors.iterator();
                        User doctor = null;
                        while (it.hasNext()) {
                            doctor = it.next();
                    %>
                        <option value ="<%=doctor.getId()%>" ><%=doctor.getFirstName() + "  "+ doctor.getLastName()%></option>
                        <%
                             }
                        %>

                    </select>
                </div>

                <div class="inputfield">
                    <label>Patient</label>
                    <select name="patient">

                    <%
                        PatientDao patientDao = new PatientDao();
                        List patients = patientDao.findAll();
                        Iterator<Patient> it2 = patients.iterator();
                        Patient patient = null;
                        while (it2.hasNext()) {
                            patient = it2.next();
                    %>
                            <option value ="<%=patient.getId()%>" ><%=patient.getFirstName() + "  "+ patient.getLastName()%></option>
                            <%
                                }
                            %>

                        </select>

                </div>


                <div class="inputfield">
                    <label>Time</label>
                    <input name="time" type="time" class="input">
                </div>
                <div class="inputfield">
                    <label>Date</label>
                    <input name="date" type="date" class="input">
                </div>

                <div class="inputfield">
                    <input type="submit" value="Save" class="btn">
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
