/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.company.resume.controller;

import context.Context;
import entity.User;
import main.dao.inter.SkillDaoInter;
import main.dao.inter.UserDaoInter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDaoInter userDao = Context.instanceUserDao();

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String nationalityIdStr = request.getParameter("nid");
            Integer nationalityId = null;
            if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
                nationalityId = Integer.parseInt(nationalityIdStr);
            }
            List<User> list = userDao.getAll(name, surname, nationalityId);

            request.setAttribute("list", list);
            request.getRequestDispatcher("users.jsp").forward(request, response);
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }

    }
}