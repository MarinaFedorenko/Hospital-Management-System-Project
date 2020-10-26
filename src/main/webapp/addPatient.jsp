<%@page import="nix.servlet.AddPatientServlet"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Medical APP</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" type="text/css" href="css/split.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
    </head>
</head>
<body>
<%@ include file="menu.jsp" %>


<div class="split right" style="align-content: center">
    <form action="<%=request.getContextPath()%> /add_patient" method="post"  >
        <div class="wrapper">
            <div class="title">
                Add new Patient
            </div>
            <div class="form">
                <div class="inputfield">
                    <label>First Name</label>
                    <input name="firstname" type="text" class="input">
                    <p class="error">${falseFirstname}</p>
                </div>
                <div class="inputfield">
                    <label>Last Name *</label>
                    <input name="lastname" type="text" class="input" required>
                    <p class="error">${falseLastname}</p>
                </div>
                <div class="inputfield">
                    <label>Gender</label>
                    <div class="custom_select">
                        <select name="gender">
                            <option value="">Select</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                </div>
                <div class="inputfield">
                    <label>Age</label>
                    <input name="age" type="text" class="input">
                </div>
                <div class="inputfield">
                    <label>Birth date</label>
                    <input name="birthdate" type="date" class="input">
                </div>
                <div class="inputfield">
                    <label>Phone Number *</label>
                    <input name="phonenumber" type="text" class="input" required>
                    <p class="error">${falsePhoneNumber}</p>
                </div>
                <div class="inputfield">
                    <label>Email Address</label>
                    <input name="email" type="text" class="input">
                    <p class="error">${falseEmail}</p>

                </div>
                <div class="inputfield">
                    <label>Marital Status</label>
                    <div class="custom_select">
                        <select name="maritalStatus">
                            <option value="single">Single</option>
                            <option value="married">Married</option>
                            <option value="divorced">Divorced</option>
                            <option value="widowed">Widowed</option>
                        </select>
                    </div>
                </div>
                <div class="inputfield">
                    <label>City</label>
                    <input name="city" type="text" class="input">
                </div>
                <div class="inputfield">
                    <label>Address</label>
                    <textarea rows="3" cols="5" name="address" class="input"></textarea>
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
