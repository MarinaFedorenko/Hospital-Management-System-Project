<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Medical APP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/menu.css">
    <link rel="stylesheet" type="text/css" href="../css/split.css">
    <link rel="stylesheet" type="text/css" href="../css/form.css">
    <link rel="stylesheet" type="text/css" href="../css/error.css">

</head>
<body>
<%@ include file="/menu.jsp" %>


<div class="split right" style="align-content: center">

    <form action="<%=request.getContextPath()%> /myProfile" method="post"  >
        <div class="wrapper">
            <div class="title">
                My Profile Information
            </div>

            <div class="form">
                <div class="inputfield">
                    <label>Username</label>
                    <p class="input">${username}</p>
                </div>
                <div class="inputfield">
                    <label>First Name</label>
                    <p class="input">${firstname}</p>
                </div>
                <div class="inputfield">
                    <label>Last Name</label>
                    <p class="input">${lastname}</p>
                </div>
                <div class="inputfield">
                    <label>Password</label>
                    <p class="input">${gender}</p>
                </div>
                <div class="inputfield">
                    <label>Age</label>
                    <p class="input">${age}</p>
                </div>
                <div class="inputfield">
                    <label>Birthday</label>
                    <p class="input">${birthdate}</p>
                </div>
                <div class="inputfield">
                    <label>Phone number</label>
                    <p class="input">${phonenumber}</p>
                </div>
                <div class="inputfield">
                    <label>Email</label>
                    <p class="input">${email}</p>
                </div>
                <div class="inputfield">
                    <label>Marital Status</label>
                    <p class="input">${maritalStatus}</p>
                </div>
                <div class="inputfield">
                    <label>City</label>
                    <p class="input">${city}</p>
                </div>
                <div class="inputfield">
                    <label>Specification</label>
                    <p class="input">${specification}</p>
                </div>

                <div class="inputfield">
                    <label>Qualification</label>
                    <p class="input">${qualification}</p>
                </div>
                <div class="inputfield">
                    <label>Hiring Date</label>
                    <p class="input">${hiringDate}</p>
                </div>
                <div class="inputfield">
                    <label>Created By</label>
                    <p class="input">${createdBy}</p>
                </div>
                <div class="inputfield">
                    <label>Modified By</label>
                    <p class="input">${modifiedBy}</p>
                </div>
                <div class="inputfield">
                    <label>Created Date</label>
                    <p class="input">${createdDate}</p>
                </div>
                <div class="inputfield">
                    <label>Modified Date</label>
                    <p class="input">${modifiedDate}</p>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
