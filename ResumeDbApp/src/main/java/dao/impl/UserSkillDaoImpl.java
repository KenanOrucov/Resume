/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import entity.Skill;
import entity.User;
import entity.UserSkill;
import main.dao.inter.AbstractDAO;
import main.dao.inter.UserSkillDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserSkillId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT " +
                    "u.*, " +
                    "us.skill_id, " +
                    "s.NAME AS skill_name, " +
                    "us.power " +
                    "FROM " +
                    "user_skill us " +
                    "LEFT JOIN USER u ON us.user_id = u.id " +
                    "LEFT JOIN skill s ON us.skill_id = s.id " +
                    "WHERE " +
                    "us.user_id = ? ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public UserSkill getById(int id) {
        UserSkill result = null;
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
                result= getUserSkill(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set user=?,skill=?, power=? where id=?");
            stmt.setObject(1, u.getUser());
            stmt.setObject(2, u.getSkill());
            stmt.setInt(3,u.getPower());
            stmt.setInt(4, u.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUserSkill(UserSkill u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (user, skill, power) values(?,?,?)");
            stmt.setObject(1, u.getUser());
            stmt.setObject(2, u.getSkill());
            stmt.setInt(3, u.getPower());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUserSkill(int id) {
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user_skill where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}