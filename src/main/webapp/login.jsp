<%@page import="nix.bean.User"%>
<%@page import="nix.servlet.Links"%>
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

    <form action="<%=request.getContextPath()%> /login" method="post"  >
        <div class="wrapper">
            <div class="title">
                Sign in
            </div>

            <div class="form">
                <div class="inputfield">
                    <label>Username</label>
                    <input name="MainUserUsername" type="text" class="input">
                    <div style="text-align: center;"> <b><span style="color: red; ">${falseUsername}</span></b> </div>
                </div>
                <div class="inputfield">
                    <label>Password</label>
                    <input name="MainUserPassword" type="text" class="input">
                    <div style="text-align: center;"> <b><span style="color: red; ">${falsePassword}</span></b> </div>
                </div>
                <div class="inputfield">
                    <div style="text-align: center;"> <b><span style="color: red; ">${falseUser}</span></b> </div>

                    <input type="submit" value="LOGIN" class="btn" >
                </div>
           </div>
        </div>
    </form>
</div>

</body>
</html>
