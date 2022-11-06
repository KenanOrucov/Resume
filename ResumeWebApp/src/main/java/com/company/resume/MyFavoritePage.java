/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.company.resume;

import context.Context;
import entity.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.dao.inter.SkillDaoInter;
import main.dao.inter.UserDaoInter;

/**
 *
 * @author User
 */
@WebServlet(name = "MyFavoritePage", urlPatterns = {"/MyFavoritePage"})
public class MyFavoritePage extends HttpServlet {

    private SkillDaoInter skillDao = Context.instanceSkillDao();
    
    private UserDaoInter userDao = Context.instanceUserDao();
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = String.valueOf(request.getParameter("name"));
        Skill s = new Skill(0,name);
        System.out.println(s.getName());
        boolean b = skillDao.addSkill(s);
        System.out.println(s);
        
       // int id = Integer.parseInt(request.getParameter("id"));
       // User u = userDao.getById(id);
       // List<User> users = userDao.getAll();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Salam</title>");            
            out.println("</head>");
            out.println("<body>");
          //  out.println(skillDao.getAll() + "<br>");
        
          out.println(skillDao.getAll());
                out.println( b  + "<br>");
            
            out.println("");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestStr = getAllDataFromRequest(req);
            String[] array = requestStr.split("=");
            String result = array[1];
          //  String name = String.valueOf(req.getParameter("name"));
          //  String surname = String.valueOf(req.getParameter("surname"));
            
         //   User u = new User(0, name, surname, null, null, null, null, null, null);
         // System.out.println(u);
         //   boolean users = userDao.addUser(u);
            
            String name = String.valueOf(req.getParameter("name"));

            Skill s = new Skill(23, result);
            skillDao.addSkill(s);
            System.out.println("request=" + requestStr);
            System.err.println(s);
        response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet MyFavoritePage</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Post sorgusu aldim" + "<br>");
                out.println("user inserted: " + s + "<br>");
                out.println("</body>");
                out.println("</html>");
            }
        }   catch (Exception ex) {
            Logger.getLogger(MyFavoritePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
    }
    
    public static String getAllDataFromRequest(HttpServletRequest request) throws Exception {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();

        return body;
    }
    
}
