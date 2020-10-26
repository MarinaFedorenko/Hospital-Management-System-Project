<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medical APP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <link rel="stylesheet" type="text/css" href="css/split.css">
    <link rel="stylesheet" type="text/css" href="css/form.css">
</head>
<body>

<%@ include file="menu.jsp" %>


<div class="split right" style="align-content: center">
    <form action="<%=request.getContextPath()%> /edit_patient" method="post"  >
        <div class="wrapper">
            <div class="title">
                Edit Patient Information
            </div>
            <div class="form">
                <input type="hidden" name="id" value="${id}">
                <div class="inputfield">
                    <label>First Name</label>
                    <input name="firstname" type="text" class="input" value="${firstname}">
                    <p class="error">${falseFirstname}</p>
                </div>
                <div class="inputfield">
                    <label>Last Name</label>
                    <input name="lastname" type="text" class="input" required value="${lastname}">
                    <p class="error">${falseLastname}</p>
                </div>
                <div class="inputfield">
                    <label>Gender</label>
                    <div class="custom_select">
                        <select name="gender" >
                            <option value="${gender}"></option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                </div>
                <div class="inputfield">
                    <label>Age</label>
                    <input name="age" type="text" class="input" value="${age}">
                </div>
                <div class="inputfield">
                    <label>Birth date</label>
                    <input name="birthdate" type="text" class="input" value="${birthdate}">
                </div>
                <div class="inputfield">
                    <label>Phone Number</label>
                    <input name="phonenumber" type="text" class="input" required value="${phonenumber}">
                    <p class="error">${falsePhoneNumber}</p>
                </div>
                <div class="inputfield">
                    <label>Email Address</label>
                    <input name="email" type="text" class="input" value="${email}">
                    <p class="error">${falseEmail}</p>

                </div>
                <div class="inputfield">
                    <label>Marital Status</label>
                    <div class="custom_select">
                        <select name="maritalStatus">
                            <option value="${maritalStatus}"></option>
                            <option value="single">Single</option>
                            <option value="married">Married</option>
                            <option value="divorced">Divorced</option>
                            <option value="widowed">Widowed</option>
                        </select>
                    </div>
                </div>
                <div class="inputfield">
                    <label>City</label>
                    <input name="city" type="text" class="input" value="${city}">
                </div>
                <div class="inputfield">
                    <label>Address</label>
                    <textarea rows="3" cols="5" name="address" class="input">${address}</textarea>
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
