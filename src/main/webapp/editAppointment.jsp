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
    <form action="<%=request.getContextPath()%> /edit_appointment" method="post"  >
        <div class="wrapper">
            <div class="title">
                Edit Appointment Information
            </div>
            <div class="form">
                <input type="hidden" name="id" value="${id}">
                <div class="inputfield" >
                    <label>Doctor</label>
                    <div name="doctor" class="input">${doctor}</div>
                    <input type="hidden" name="doctorId" value="${doctorId}">

                </div>
                <div class="inputfield" >
                    <label>Patient </label>
                    <div name="patient" class="input">${patient}</div>
                    <input type="hidden" name="patientId" value="${patientId}">
                </div>
                <div class="inputfield">
                    <label>Time</label>
                    <input name="time" type="time" class="input" value="${time}">
                    <p class="error">${falseTime}</p>
                </div>
                <div class="inputfield">
                    <label>Date</label>
                    <input name="date" type="date" class="input" required value="${date}">
                    <p class="error">${falseDate}</p>
                </div>
                <div class="inputfield">
                    <label>ModifiedBy</label>
                    <input name="modifiedBy" type="text" class="input" value="${modifiedBy}">
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
