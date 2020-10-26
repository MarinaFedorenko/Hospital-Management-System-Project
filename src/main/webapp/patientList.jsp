<%@page import="nix.dao.PatientDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nix.bean.Patient" %>
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
    </script>
</head>
<body>
<%@ include file="menu.jsp" %>


<div class="split right" style="align-content: center">

    <section class="intro">
        <div class="container wrapper" style="max-width: 100%">
            <div class="title">
                List of Patients
            </div>
            <br>
            <form action="<%=request.getContextPath()%> /patient_list" method="post">
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
                    <b><span style="color: red; ">${falseLastname}</span></b>
                    <b><span style="color: red; ">${falsePhonenumber}</span></b>
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
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">City</th>
                        <th scope="col">Address</th>
                        <th scope="col">Created by</th>
                        <th scope="col">CreatedDatetime</th>
                        <th scope="col">Edit</th>

                    </tr>
                    </thead>



                    <% List pList = (List) request.getAttribute("patients");
                        if(pList!=null){
                            for(int i=0; i<pList.size(); i++){
                    %>
                    <tr id="tr">
                        <td scope="row"><input type="checkbox" class="case" name="patientIds" value="<%=((Patient) pList.get(i)).getId()%>"> </td>
                        <td><%= ((Patient) pList.get(i)).getFirstName()+" "+((Patient) pList.get(i)).getLastName()%></td>
                        <td><%= ((Patient) pList.get(i)).getAge()%></td>
                        <td><%= ((Patient) pList.get(i)).getBirthDate()%></td>
                        <td><%= ((Patient) pList.get(i)).getPhoneNumber()%></td>
                        <td><%= ((Patient) pList.get(i)).getEmail()%></td>
                        <td><%= ((Patient) pList.get(i)).getCity()%></td>
                        <td><%= ((Patient) pList.get(i)).getAddress()%></td>
                        <td><%= ((Patient) pList.get(i)).getCreatedBy()%></td>
                        <td><%= ((Patient) pList.get(i)).getCreatedDatetime()%></td>
                        <td><a class="btn btn-primary" href="edit_patient?id=<%=((Patient) pList.get(i)).getId()%>">Edit</a></td>


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
