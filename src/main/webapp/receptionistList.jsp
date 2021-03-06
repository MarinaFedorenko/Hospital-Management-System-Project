<%@page import="nix.dao.UserDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>Medical APP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <link rel="stylesheet" type="text/css" href="css/split.css">
    <link rel="stylesheet" type="text/css" href="css/form.css">
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <link rel="stylesheet" type="text/css" href="css/button.css">


    <script language="javascript">
        $(function() {
            $("#selectall").click(function() {
                $('.case').attr('checked', this.checked);
            });
            $(".case").click(function() {

                if ($(".case").length == $(".case:checked").length) {
                    $("#selectall").attr("checked", "checked");
                } else {
                    $("#selectall").removeAttr("checked");
                }

            });
        });

    </script>
    <script>
        tr.onmouseover = function(){
            this.style.fontWeight='bold';
            this.style.color='cadetblue';
        }
        tr.onmouseout = function(){
            this.style.fontWeight='';
            this.style.color='';
        }
    </script>
</head>
<body>
<%@ include file="menu.jsp" %>


<div class="split right" style="align-content: center">

    <section class="intro">
        <%--        <div class="intro-content">--%>
        <div class="container wrapper" style="max-width: 100%">
            <div class="title">
                List of Receptionists
            </div>
            <br>
            <form action="<%=request.getContextPath()%> /receptionist_list" method="post">
                <table>
                    <thead class="thead-dark" style="align-content: center;">
                    <tr>
                        <th scope="col">Last Name</th>
                        <th scope="col">Phone Number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td style="border: 0px"><input type="text" name="lastname"
                                   placeholder="Enter Last Name"
                                   class="form-control input-md"></td>
                        <td style="border: 0px"><input type="text" name="phonenumber"
                                                       placeholder="Enter phone number"
                                                       class="form-control input-md"></td>
                        <td style = "border: 0px"><input type="submit" class="btn" name="search" value="Search"></td>
                        <td style ="border: 0px"><input type="submit" class="btn" name="findAll" value="Find All"></td>
                    </tr>
                    </tbody>
                </table>
                <br>


                <div style="text-align: center;">
                    <b><span style="color: red; "> ${falseLastname} </span></b>
                    <b><span style="color: red; "> ${falsePhonenumber} </span></b>
                    <b><span style="color: red; "> ${falseReceptionist} </span></b>
                    <b><span style="color: green; "> ${successUpdate} </span></b>
                    <b><span style="color: green; "> ${successInsert} </span></b>
                    <b><span style="color: green; "> ${successDelete} </span></b>
                    <b><span style="color: red; "> ${failureUpdate} </span></b>
                    <b><span style="color: red; "> ${failureInsert} </span></b>
                    <b><span style="color: red; "> ${failureDelete} </span></b>

                </div>


                <table class="content-table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Select All<br><input type="checkbox" id="selectall"></th>
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Specification</th>
                        <th scope="col">Qualification</th>
                        <th scope="col">HiringDate</th>
                        <th scope="col">CreatedDatetime</th>
                        <th scope="col">Edit</th>

                    </tr>
                    </thead>

                    <% List rList = (List) request.getAttribute("receptionists");
                        if(rList!=null){
                            for(int i=0; i<rList.size(); i++){
                    %>
                    <tr id="tr">
                        <td scope="row"><input type="checkbox" class="case" name="receptionistIds" value="<%=((User) rList.get(i)).getId()%>"> </td>
                        <td><%= ((User) rList.get(i)).getFirstName()+" "+((User) rList.get(i)).getLastName()%></td>
                        <td><%= ((User) rList.get(i)).getAge()%></td>
                        <td><%= ((User) rList.get(i)).getBirthDate()%></td>
                        <td><%= ((User) rList.get(i)).getPhoneNumber()%></td>
                        <td><%= ((User) rList.get(i)).getEmail()%></td>
                        <td><%= ((User) rList.get(i)).getSpecialization()%></td>
                        <td><%= ((User) rList.get(i)).getQualification()%></td>
                        <td><%= ((User) rList.get(i)).getHiringDate()%></td>
                        <td><%= ((User) rList.get(i)).getCreatedDatetime()%></td>
                        <td><a class="btn btn-primary" href="edit_receptionist?id=<%=((User) rList.get(i)).getId()%>">Edit</a></td>


                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
                <table>
                    <thead>
                    <tr>

                        <td><input type="submit" name="new" class="btn"  value="New"></td>
                        <td><input type="submit" name="delete" class="btn" value="Delete"></td>


                    </thead>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>
