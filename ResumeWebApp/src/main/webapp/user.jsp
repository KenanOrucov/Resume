<%-- 
    Document   : user
    Created on : Oct 26, 2022, 9:23:55 PM
    Author     : User
--%>

<%@page import="entity.User"%>
<%@page import="main.dao.inter.UserDaoInter"%>
<%@page import="context.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Salam</title>
    </head>
    <body>
        <%
            UserDaoInter userDao = Context.instanceUserDao();
            
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
        User u = userDao.getById(6);

        %>
        
        <div>
            <form action="UserController" method="POST">
                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                <label for="name">name</label>
                <input type="text" name="name" value="<%=u.getName()%>"/>
                <br/>
                <label for="surname">surname</label>
                <input type="text" name="surname" value="<%=u.getSurname()%>"/>
        
                <input type="submit" name="save" value="Save"/>
            </form>
        </div>
    </body>
</html>
