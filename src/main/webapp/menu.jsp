<%@page import="nix.servlet.LoginServlet"%>
<%@page import="nix.servlet.Links"%>
<%@page import="nix.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Medical APP</title>

    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <link rel="stylesheet" type="text/css" href="css/split.css">
    <link rel="stylesheet" type="text/css" href="css/form.css">
    <link rel="stylesheet" type="text/css" href="css/submenu.css">

</head>
<body>

<%
    User user = (User) session.getAttribute("user");

    boolean userLoggedIn = user != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += user.getFirstName() + user.getLastName()+ " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<div class="split left">
    <div class="sidenav " >
        <a href="<%=Links.HOME%>" class="menu__link">Home</a>
        <%if(userLoggedIn){ %>

        <%if(user.getRoleId()==1){%>
        <a href="#" onclick="openbox('doctors'); return false" >Doctor</a>
        <div  id="doctors" style=" background-color:white; display: none;" class=":hover{color:black;}" >
            <a href="<%=Links.ADD_DOCTOR%>"  style="font-size: larger;  color: cadetblue;">Add Doctor</a>
            <a href="<%=Links.DOCTOR_LIST%>"  style="font-size: larger;  color: cadetblue;">Doctor List</a>
        </div>
        <a href="#" onclick="openbox('receptionists'); return false">Receptionist</a>
        <div id="receptionists" style="background-color:white; display: none;">
            <a href="<%=Links.ADD_RECEPTIONIST%>" style="font-size: larger;  color: cadetblue;">Add Receptionist</a>
            <a href="<%=Links.RECEPTIONIST_LIST%>" style="font-size: larger;  color: cadetblue;">Receptionist List</a>
        </div>
        <a href="#" onclick="openbox('patients'); return false">Patient</a>
        <div id="patients" style="background-color:white; display: none;">
            <a href="<%=Links.ADD_PATIENT%>" style="font-size: larger;  color: cadetblue;">Add Patient</a>
            <a href="<%=Links.PATIENT_LIST%>" style="font-size: larger;  color: cadetblue;">Patient List</a>
        </div>
        <a href="#" onclick="openbox('appointments'); return false">Appointment</a>
        <div id="appointments" style="background-color:white; display: none;">
            <a href="<%=Links.ADD_APPOINTMENT%>" style="font-size: larger;  color: cadetblue;">Add Appointment</a>
            <a href="<%=Links.APPOINTMENT_LIST%>" style="font-size: larger;  color: cadetblue;">Appointment List</a>
        </div>

        <%}else if(user.getRoleId()==2){%>

        <a href="#" onclick="openbox('patients'); return false">Patient</a>
        <div id="patients" style="background-color:white; display: none;">
            <a href="<%=Links.ADD_PATIENT%>" style="font-size: larger;  color: cadetblue;">Add Patient</a>
            <a href="<%=Links.PATIENT_LIST%>" style="font-size: larger;  color: cadetblue;">Patient List</a>
        </div>
        <a href="#" onclick="openbox('appointments'); return false">Appointment</a>
        <div id="appointments" style="background-color:white; display: none;">
            <a href="<%=Links.ADD_APPOINTMENT%>" style="font-size: larger;  color: cadetblue;">Add Appointment</a>
            <a href="<%=Links.APPOINTMENT_LIST%>" style="font-size: larger;  color: cadetblue;">Appointment List</a>
        </div>

        <%}else if(user.getRoleId()==3){%>

        <a href="<%=Links.PATIENT_LIST%>" class="menu__link">Patient List</a>
        <a href="<%=Links.APPOINTMENT_LIST%>" class="menu__link">Appointment List</a>

        <%}%>

        <a href="#" onclick="openbox('profile'); return false">Hi, ${firstname} ${lastname} (${role})</a>
        <div id="profile" style="background-color:white; display: none;">
            <a href="<%=Links.MY_PROFILE%>" style="font-size: larger;  color: cadetblue;">My Profile</a>
            <a href="<%=Links.CHANGE_PASSWORD%>" style="font-size: larger;  color: cadetblue;">Change password</a>
            <a href="<%=Links.LOGOUT%>" style="font-size: larger;  color: cadetblue;">Logout</a>
        </div>

        <%}else{%>
        <li><a href="<%=Links.LOGIN%>">Login</a></li>
        <%} %>

    </div>
</div>


<script type="text/javascript">

    function openbox(id){
        display = document.getElementById(id).style.display;

        if(display=='none'){
            document.getElementById(id).style.display='block';
        }else{
            document.getElementById(id).style.display='none';
        }
    }
</script>
</body>
</html>
