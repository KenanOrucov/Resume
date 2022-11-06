<%-- 
    Document   : user
    Created on : Oct 26, 2022, 9:23:55 PM
    Author     : User
--%>

<%@page import="entity.User"%>
<%@page import="main.dao.inter.UserDaoInter"%>
<%@page import="context.Context"%>
<%@ page import="main.dao.inter.SkillDaoInter" %>
<%@ page import="entity.Country" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
        <title>Salam</title>
    </head>
    <body>
        <%

    //    if(request.getParameter("save") != null && request.getParameter("save").equals("Save")){
    //    int id = Integer.valueOf(request.getParameter("id"));
   //     String name = request.getParameter("name");
   //     String surname = request.getParameter("surname");
   //     
   //     System.out.println("name=" + name);
   //     System.out.println("surname=" + surname);
   //     
   //     User user = userDao.getById(id);
    //    user.setName(name);
   //     user.setSurname(surname);
   //         
   //     userDao.updateUser(user);
   //         }
       // User u = userDao.getById(6);


            User user = (User) request.getAttribute("user");
            List<Country> listCountry = (List<Country>) request.getAttribute("countries");
                %>

        <div>
            <div class="container">
            <form action="userdetail" method="POST">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
<%--                <input type="hidden" name="action" value="update">--%>
                <div class="row mt-3 g-3">
                    <div class="col-6">
                        <label class="mb-2">Name</label>
                        <input type="text" class="form-control" name="name" value="<%=user.getName()%>" placeholder="<%=user.getName()%>"/>
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Surname</label>
                        <input type="text" class="form-control" name="surname" value="<%=user.getSurname()%>" placeholder="<%=user.getSurname()%>">
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Email</label>
                        <input type="email" class="form-control" name="email" value="<%=user.getEmail()%>" placeholder="<%=user.getEmail()%>">
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Phone</label>
                        <input type="text" class="form-control" name="phone" value="<%=user.getPhone()%>" placeholder="<%=user.getPhone()%>">
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Birth Date</label>
                        <input type="date" class="form-control" name="birthdate" value="<%=user.getBirthDate()%>" placeholder="<%=user.getBirthDate()%>">
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Address</label>
                        <input type="text" class="form-control" name="address" value="<%=user.getAddress()%>" placeholder="<%=user.getAddress()%>">
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Birth Place</label>
                        <select class="custom-select" id="BirthPlace-id" name="birthplace">
                            <option selected value="<%=user.getBirthPlace().getId()%>"><%=user.getBirthPlace().getName()%></option>
                            <%for (Country c: listCountry){%>
                            <option name="birthplace" value="<%=c.getId()%>"><%=c.getName()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="col-6">
                        <label class="mb-2">Nationality</label>
                        <select class="custom-select mr-sm-2" id="Nationality-id" name="nationality">
                            <option selected value="<%=user.getNationality().getId()%>"><%=user.getNationality().getNationality()%></option>
                            <%for (Country c: listCountry){%>
                            <option value="<%=c.getId()%>"><%=c.getNationality()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="col-12">
                        <label class="mb-2">Description</label>
                        <textarea class="form-control" rows="4" cols="40" name="description" value="<%=user.getProfileDesc()%>"><%=user.getProfileDesc()%></textarea>
                    </div>
                </div>
                <input class="btn btn-success mt-3" style="display: inline; float:right" type="submit" name="action" value="Save"/>
            </form>
        </div>
        </div>

    </body>
</html>
