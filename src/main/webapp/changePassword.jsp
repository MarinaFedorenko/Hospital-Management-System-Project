<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Medical APP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/menu.css">
    <link rel="stylesheet" type="text/css" href="../css/split.css">
    <link rel="stylesheet" type="text/css" href="../css/form.css">
    <link rel="stylesheet" type="text/css" href="../css/error.css"></head>
<body>
<%@ include file="/menu.jsp" %>


<div class="split right" style="align-content: center">

    <form action="<%=request.getContextPath()%> /change_password" method="post"  >
        <div class="wrapper">
            <div class="title">
                Sign in
            </div>

            <div class="form">
                <div class="inputfield">
                    <label>Enter current password</label>
                    <input name="currentPassword" type="password" class="input">
                </div>
                <div class="inputfield">
                    <label>Enter new password</label>
                    <input name="newPassword" type="password" class="input">
                </div>
                <div class="inputfield">
                    <div style="text-align: center;"> <b><span style="color: red; ">${theSame}</span></b> </div>
                    <div style="text-align: center;"> <b><span style="color: red; ">${falsePassword}</span></b> </div>

                    <input type="submit" value="Save" class="btn" >
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
