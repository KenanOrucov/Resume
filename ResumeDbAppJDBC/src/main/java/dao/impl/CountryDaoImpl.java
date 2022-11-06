/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import entity.Country;
import entity.User;
import main.dao.inter.AbstractDAO;
import main.dao.inter.CountryDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        Country country= new Country(id, name, nationality);
        return country;
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try(Connection c = connection()){
            Statement stmt = c.createStatement();
            stmt.execute("select * from country");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                Country country = getCountry(rs);

                result.add(country);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateCountry(Country u) {
        try(Connection c = connection()) {
            PreparedStatement prstmt = c.prepareStatement("update country set name =?, nationality = ? where id = ?");
            prstmt.setString(1, u.getName());
            prstmt.setString(2, u.getNationality());
            prstmt.setInt(3, u.getId());

            return prstmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Country getById(int id) {
        Country result = null;
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();                           //statement beyanat demekdi, db-a gedecek melumatlari aparmaqdi isi
            stmt.execute("select * from country where id= " + id);
            ResultSet rs = stmt.getResultSet();                          // resultset ise geriye gelen neticedir

            while (rs.next()) {
                result= getCountry(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addCountry(Country u) {
        try(Connection c = connection()){
            PreparedStatement pstmt = c.prepareStatement("insert into country (name, nationality) values(?, ?)");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getNationality());
            return pstmt.execute();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCountry(int id) {
        try(Connection c = connection()){
            Statement stmt = c.createStatement();
            return stmt.execute("delete from country where id=" + id);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
