<%@ page import="java.util.List" %>
<%@ page import="nix.bean.Appointment" %>
<%@ page import="nix.dao.UserDao" %>
<%@ page import="nix.dao.PatientDao" %>
<%@ page import="nix.bean.Patient" %>
<%@ page import="java.nio.file.attribute.UserDefinedFileAttributeView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    </script></head>
<body>
<%@ include file="menu.jsp" %>



<div class="split right" style="align-content: center">

    <section class="intro">
        <%--        <div class="intro-content">--%>
        <div class="container wrapper" style="max-width: 100%">
            <div class="title">
                List of Appointments
            </div>
            <br>
            <form action="<%=request.getContextPath()%> /appointment_list" method="post">
                <table>
                    <thead class="thead-dark" style="align-content: center;">
                    <tr>
                        <th scope="col">Doctor Last Name</th>
                        <th scope="col">Patient Last Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td style="border: 0px"><input type="text" name="doctor"
                                                       class="form-control input-md"></td>
                        <td style="border: 0px"><input type="text" name="patient"
                                                       class="form-control input-md"></td>
                        <td style = "border: 0px"><input type="submit" class="btn" name="search" value="Search"></td>
                        <td style ="border: 0px"><input type="submit" class="btn" name="findAll" value="Find All"></td>
                    </tr>
                    </tbody>
                </table>
                <br>


                <div style="text-align: center;">
                    <b><span style="color: red; ">${falseDoctor}</span></b>
                    <b><span style="color: red; ">${falsePatient}</span></b>
                    <b><span style="color: green; ">${successUpdate}</span></b>
                    <b><span style="color: green; ">${successInsert}</span></b>
                    <b><span style="color: green; ">${successDelete}</span></b>
                    <b><span style="color: red; ">${failureUpdate}</span></b>
                    <b><span style="color: red; ">${failureInsert}</span></b>
                    <b><span style="color: red; ">${failureDelete}</span></b>

                </div>


                <table class="content-table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Select All<br><input type="checkbox" id="selectall"></th>
                        <th scope="col">Doctor</th>
                        <th scope="col">Patient</th>
                        <th scope="col">Time</th>
                        <th scope="col">Date</th>
                        <th scope="col">CreatedBy</th>
                        <th scope="col">ModifiedBy</th>
                        <th scope="col">Created</th>
                        <th scope="col">Modified</th>
                        <th scope="col">Edit</th>

                    </tr>
                    </thead>


                    <% List aList = (List) request.getAttribute("appointments");
                        UserDao userDao = new UserDao();
                        PatientDao patientDao = new PatientDao();
                        User doctor = null;
                        Patient patient = null;
                        if(aList!=null){
                            for(int i=0; i<aList.size(); i++){
                                doctor = userDao.findById(((Appointment) aList.get(i)).getDoctorId());
                                patient = patientDao.findById(((Appointment) aList.get(i)).getPatientId());
                    %>
                    <tr id="tr">
                        <td scope="row"><input type="checkbox" class="case" name="appointmentsIds" value="<%=((Appointment) aList.get(i)).getId()%>"> </td>
                        <td><%= doctor.getFirstName()+" "+doctor.getLastName()%></td>
                        <td><%= patient.getFirstName()+" "+patient.getLastName()%></td>
                        <td><%= ((Appointment) aList.get(i)).getTime()%></td>
                        <td><%= ((Appointment) aList.get(i)).getDate()%></td>
                        <td><%= ((Appointment) aList.get(i)).getCreatedBy()%></td>
                        <td><%= ((Appointment) aList.get(i)).getModifiedBy()%></td>
                        <td><%= ((Appointment) aList.get(i)).getCreatedDatetime()%></td>
                        <td><%= ((Appointment) aList.get(i)).getModifiedDatetime()%></td>
                        <td><a class="btn btn-primary" href="edit_appointment?id=<%=((Appointment) aList.get(i)).getId()%>">Edit</a></td>
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
