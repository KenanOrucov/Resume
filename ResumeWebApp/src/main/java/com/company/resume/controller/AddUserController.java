/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.company.resume.controller;

import context.Context;
import entity.Country;
import entity.User;
import main.dao.inter.CountryDaoInter;
import main.dao.inter.SkillDaoInter;
import main.dao.inter.UserDaoInter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/adduser"})
public class AddUserController extends HttpServlet {


    private UserDaoInter userDao = Context.instanceUserDao();

    private SkillDaoInter skillDao = Context.instanceSkillDao();

    private CountryDaoInter countryDao = Context.instanceCountryDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");
        System.out.println("action = " + action);
        if (action.equals("Save")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Date birthDate = Date.valueOf(request.getParameter("birthdate"));
            String address = request.getParameter("address");
            int nationality = Integer.valueOf(request.getParameter("nationality"));
            int birthplace = Integer.valueOf(request.getParameter("birthplace"));
            String description = request.getParameter("description");
            String password = request.getParameter("password");

//            System.out.println(birthplace);
            System.out.println(nationality);
            System.out.println(birthplace);

            User user = new User();
            Country countryNationality = countryDao.getById(nationality);
            Country countryBirthPlace = countryDao.getById(birthplace);

//            System.out.println(countryBirthPlace);
//            System.out.println(countryBirthPlace.getName());
            user.setId(8);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthDate(birthDate);
            user.setAddress(address);
            user.setBirthPlace(countryBirthPlace);
            user.setNationality(countryNationality);
            user.setProfileDesc(description);
            user.setPassword(password);

            System.out.println(user);
            userDao.addUser(user);

        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Country> list = countryDao.getAll();
            request.setAttribute("countries", list);
            request.getRequestDispatcher("adduser.jsp").forward(request, response);
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}