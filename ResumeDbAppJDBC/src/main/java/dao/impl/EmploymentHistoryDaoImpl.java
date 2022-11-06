/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;


import entity.Country;
import entity.EmploymentHistory;
import entity.User;
import main.dao.inter.AbstractDAO;
import main.dao.inter.EmploymentHistoryDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");
        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory getById(int id) {
        EmploymentHistory result = null;
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();                           //statement beyanat demekdi, db-a gedecek melumatlari aparmaqdi isi
            stmt.execute("SELECT " +
                            "u.*," +
                            "n.nationality as nationality, " +
                            "c.name as birthplace " +
                            "" +
                            "FROM USER as u " +
                            "LEFT JOIN country as n ON U.nationality_id = n.id "+
                            "LEFT JOIN country as c ON U.birthplace_id = c.id where u.id=" + id);
            ResultSet rs = stmt.getResultSet();                          // resultset ise geriye gelen neticedir

            while (rs.next()) {
                result= getEmploymentHistory(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory u) {
        try(Connection c = connection()) {
            PreparedStatement prstmt = c.prepareStatement("update employment_history set id =?, header = ?, beginDate = ?, endDate = ?, jobDescription = ?, user = ? where id = " + u.getId());
            prstmt.setInt(1, u.getId());
            prstmt.setString(2, u.getHeader());
            prstmt.setDate(3, (java.sql.Date) u.getBeginDate());
            prstmt.setDate(4, (java.sql.Date) u.getEndDate());
            prstmt.setString(5, u.getJobDescription());
            prstmt.setObject(6, u.getUser());

            return prstmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into employment_history (header, beginDate,endDate, jobDescription, user) values(?,?,?,?,?)");
            stmt.setString(1, u.getHeader());
            stmt.setDate(2, (java.sql.Date) u.getBeginDate());
            stmt.setDate(3, (java.sql.Date) u.getEndDate());
            stmt.setString(4, u.getJobDescription());
            stmt.setObject(5, u.getUser());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from employment_history where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
