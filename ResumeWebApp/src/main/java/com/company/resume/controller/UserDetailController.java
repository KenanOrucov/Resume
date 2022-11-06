/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.company.resume.controller;

import context.Context;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Country;
import entity.User;
import main.dao.inter.CountryDaoInter;
import main.dao.inter.SkillDaoInter;
import main.dao.inter.UserDaoInter;

/**
 *
 * @author User
 */
@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {

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

            System.out.println(birthplace);

            User user = userDao.getById(id);
            Country countryNationality = countryDao.getById(nationality);
            Country countryBirthPlace = countryDao.getById(birthplace);

            System.out.println(countryBirthPlace);
            System.out.println(countryBirthPlace.getName());
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthDate(birthDate);
            user.setAddress(address);
            user.setBirthPlace(countryBirthPlace);
            user.setNationality(countryNationality);
            user.setProfileDesc(description);

            System.out.println(user.getName());
            userDao.updateUser(user);
        } else if (action.equals("delete")) {
            userDao.removeUser(id);
        }
        response.sendRedirect("users");
        //***************************************************************************************************

      //  response.getOutputStream().println("Successfully updated!!!");      /*yeni sehife acir ver bu yazini cap edir*/
      //  response.getOutputStream().close();

        //***************************************************************************************************

//        String name = String.valueOf(request.getParameter("name"));
//        String surname = String.valueOf(request.getParameter("surname"));
//        System.out.println("name=" + name);
//        System.out.println("surname = " + surname);
//        User user = new User(9, name, surname,"email", "phone", null,null,null,null);
//        userDao.addUser(user);
//
//        response.sendRedirect("users.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }

            Integer userId = Integer.valueOf(userIdStr);
            UserDaoInter userDao = Context.instanceUserDao();
            List<Country> list = countryDao.getAll();
            User user = userDao.getById(userId);
            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }

            request.setAttribute("owner", true);
            request.setAttribute("user", user);
            request.setAttribute("countries", list);
            request.getRequestDispatcher("userdetail.jsp").forward(request,response);
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }

}
