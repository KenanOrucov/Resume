<%-- 
    Document   : user
    Created on : Oct 26, 2022, 9:23:55 PM
    Author     : User
--%>

<%@page import="entity.User"%>
<%@page import="main.dao.inter.UserDaoInter"%>
<%@page import="context.Context"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/users.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
        <script type="text/javascript" src="assets/js/users.js"></script>      <%--javascript-i add eliyirik --%>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Salam</title>

    </head>
    <body>

        <%
                User user = (User) session.getAttribute("loggedInUser");
                List<User> list = (List) request.getAttribute("list");
        %>
        <div class="container mycontainer col-12">
            <div class="row">
                <div class="col-4 g-3">
                    <form action="users" method="GET">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input class="form-control" placeholder="Enter name" type="text" name="name" value="" id="whatIamtyping"/>
                </div>
                <div class="form-group mt-3">
                    <label for="surname">Surname</label>
                    <input class="form-control" placeholder="Enter surname" type="text" name="surname" value=""/>
                </div>
                        <input class="btn btn-primary mt-3" type="submit" name="search" value="Search" id="btnsearch"/>
                 </form>
                    <form action="adduser" method="get">
                        <input type="hidden" name="action" value="update"/>
                        <input class="btn btn-success mt-3" type="submit" name="action" value="Add" id="add-user"/>
                    </form>
            </div>
        </div>
            <hr/>
            <div>
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>surname</th>
                <th>nationality</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
            for (User u: list){
            %>
            <tr>
                <td><%=u.getName()%></td>
                <td><%=u.getSurname()%></td>
                <td><%=u.getNationality().getName()==null?"N/A":u.getNationality().getName()%></td>
                <td style="width: 5px">

                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button class="btn btn-danger" type="submit" name="action"
                                data-toggle="modal" data-target="#exampleModal"
                        onclick="setIdForDelete(<%=u.getId()%>)">
                            <i class="fas fa-trash-alt"></i>
                        </button>

                </td>
                <td style="width: 5px">
                    <form action="userdetail" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="update"/>
                        <button class="btn btn-secondary" type="submit" value="update" name="action">
                            <i class="fas fa-pen-square"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
        </div>


        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="id" value="" id="idForDelete">
                            <input type="hidden" name="action" value="delete">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-danger" value="Delete"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
