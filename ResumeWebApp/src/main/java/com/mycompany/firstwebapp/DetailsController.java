/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.firstwebapp;

import context.Context;
import dao.impl.UserDaoImpl;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.dao.inter.UserDaoInter;

/**
 *
 * @author User
 */
@WebServlet(name = "DetailsController", urlPatterns = {"/DetailsController"})
public class DetailsController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User u = userDao.getById(6);
            
            int id = Integer.valueOf(request.getParameter("id"));
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String birthdate = String.valueOf(request.getParameter("birthdate"));
            
            Date dateUtil = sdf.parse(birthdate);
            long l = dateUtil.getTime();
            java.sql.Date db = new java.sql.Date(l);
            
            System.out.println("address = " + address);
            System.out.println("phone= " + phone);
            System.out.println("email = " + email);
            System.out.println("birthdate = " + birthdate);
            
            u.setAddress(address);
            u.setPhone(phone);
            u.setEmail(email);
            u.setBirthDate(db);
            
            userDao.updateUser(u);
            response.sendRedirect("profile.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


}
