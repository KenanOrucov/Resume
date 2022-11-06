<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/5/2022
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/css/users.css">
</head>
<body class="body-background">
<div class="container">
  <form action="login" method="POST">
    <div class="col-12 login_fix mt-3">
      <center>
        <h1 style="color: black">Login</h1>
      </center>
      <div class="form-group">
        <label style="color: black">Email</label>
        <input type="email" style="width: 300px" class="form-control" name="email" placeholder="email@example.com">
      </div>
      <div class="form-group">
        <label class="mt-3" style="color: black">Password</label>
        <input type="password" style="width: 300px" class="form-control" name="password" placeholder="Password">
      </div>
      <button name="login" type="submit" class="btn btn-primary mt-3" id="login-id">Login</button>
    </div>
  </form>
</div>
</body>
</html>
