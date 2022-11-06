/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import entity.Skill;
import entity.User;
import main.dao.inter.AbstractDAO;
import main.dao.inter.SkillDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");

        Skill skill = new Skill(id, name);
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();

        try(Connection c = connection()){
            PreparedStatement stmt = c.prepareStatement("select * from skill");
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                Skill skill = getSkill(rs);

                result.add(skill);
            }
        }catch (Exception ex){
        }
        return  result;
    }

    @Override
    public Skill getById(int id) {
        Skill result = null;
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
                result= getSkill(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateSkill(Skill u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user set name =? where id=?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSkill(Skill u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill (name) values(?)");
            stmt.setString(1, u.getName());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeskill(int id) {
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from skill where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
