<%@ page import="entity.User" %>
<%@ page import="entity.Country" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/6/2022
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
</head>
<body>
<%
    User user = new User();
    List<Country> listCountry = (List<Country>) request.getAttribute("countries");
%>
<div>
    <div class="container">
        <form action="adduser" method="POST">
            <input type="hidden" name="id" value="<%=user.getId()%>"/>
            <div class="row mt-3 g-3">
                <div class="col-6">
                    <label class="mb-2">Name</label>
                    <input type="text" class="form-control" name="name" value="<%=user.getName()%>" placeholder="Name"/>
                </div>
                <div class="col-6">
                    <label class="mb-2">Surname</label>
                    <input type="text" class="form-control" name="surname" value="<%=user.getSurname()%>" placeholder="Surname">
                </div>
                <div class="col-6">
                    <label class="mb-2">Email</label>
                    <input type="email" class="form-control" name="email" value="<%=user.getEmail()%>" placeholder="Email">
                </div>
                <div class="col-6">
                    <label class="mb-2">Phone</label>
                    <input type="text" class="form-control" name="phone" value="<%=user.getPhone()%>" placeholder="Phone">
                </div>
                <div class="col-6">
                    <label class="mb-2">Birth Date</label>
                    <input type="date" class="form-control" name="birthdate" value="<%=user.getBirthDate()%>" placeholder="Birth Date">
                </div>
                <div class="col-6">
                    <label class="mb-2">Address</label>
                    <input type="text" class="form-control" name="address" value="<%=user.getAddress()%>" placeholder="Address">
                </div>
                <div class="col-6">
                    <label class="mb-2">Birth Place</label>
                    <select class="custom-select" id="BirthPlace-id" name="birthplace">
                        <option selected value="Country"></option>
                        <%for (Country c: listCountry){%>
                        <option name="birthplace" value="<%=c.getId()%>"><%=c.getName()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-6">
                    <label class="mb-2">Nationality</label>
                    <select class="custom-select mr-sm-2" id="Nationality-id" name="nationality">
                        <option selected value="Country"></option>
                        <%for (Country c: listCountry){%>
                        <option value="<%=c.getId()%>"><%=c.getNationality()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-12">
                    <label class="mb-2">Description</label>
                    <textarea class="form-control" rows="4" cols="40" name="description" value="<%=user.getProfileDesc()%>">Description</textarea>
                </div>
                <div class="col-6">
                    <label class="mb-2">Password</label>
                    <input type="text" class="form-control" name="password" value="<%=user.getPassword()%>" placeholder="Surname">
                </div>
            </div>
            <input class="btn btn-success mt-3" style="display: inline; float:right" type="submit" name="action" value="Save"/>
        </form>
    </div>
</div>
</body>
</html>
