
package dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import context.Context;
import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import main.dao.inter.AbstractDAO;
import main.dao.inter.CountryDaoInter;
import main.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();


    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr );
        Country birthPlace = new Country(birthplaceId, birthPlaceStr, null );

        return new User(id, name, surname, email, phone, profileDescription, address, birthDate, nationality, birthPlace);
    }

    private User getUserSimple(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthDate = rs.getDate("birthdate");

        User user = new User(id, name, surname, email, phone, profileDescription, address, birthDate, null, null);
        user.setPassword(rs.getString("password"));
        return user;
    }

        @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try(Connection c = connection()) {              // en axirda connectionu close etmek lazimdir, try-catchin auto close funkisyasidir bu
                                                        // hemcinin connectionu close etmek resultset ve statementi close etmek demekdir

            String sql = "SELECT " +
                    "u.*, " +
                    "n.nationality as nationality, " +
                    "c.name as birthplace " +
                    "" +
                    " FROM USER as u " +
                    "LEFT JOIN country as n ON U.nationality_id = n.id "+
                    "LEFT JOIN country as c ON U.birthplace_id = c.id where 1=1 ";
            if (name != null && !name.trim().isEmpty()){
                sql += " and u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()){
                sql += " and u.surname=? ";
            }
            if (nationalityId != null ){
                sql += " and u.nationality_id=? ";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if (name !=null && !name.trim().isEmpty()){
                stmt.setString(i, name);
                i++;
            }

            if (surname !=null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId !=null){
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);

                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                result = getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                result = getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();                           //statement beyanat demekdi, db-a gedecek melumatlari aparmaqdi isi
            stmt.execute("SELECT " +
                            "u.*," +
                            "n.nationality as nationality, " +
                            "c.name as birthplace " +
                            "" +
                            "FROM USER as u " +
                            "LEFT JOIN country as n ON U.nationality_id = n.id "+
                            "LEFT JOIN country as c ON U.birthplace_id = c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();                          // resultset ise geriye gelen neticedir

            while (rs.next()) {
                result= getUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u){
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user set name =?,surname=?,email=?, phone=?, profile_description = ?, address = ?, birthdate=?, birthplace_id=?, nationality_id = ? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean addUser(User u) {
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (name, surname, email,profile_description, address, phone,password, birthdate, birthplace_id, nationality_id) values(?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4,u.getProfileDesc());
            stmt.setString(5, u.getAddress());
            stmt.setString(6, u.getPhone());
//            stmt.setString(7, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(7, u.getPassword());
            stmt.setDate(8, u.getBirthDate());
            stmt.setInt(9, u.getBirthPlace().getId());
            stmt.setInt(10, u.getNationality().getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        UserDaoInter userDao = Context.instanceUserDao();
        User user = new User();
        user.setName("Test");
        user.setSurname("Test");
        user.setPhone("Test");
        user.setEmail("Test@test.com");
        user.setProfileDesc("Test");
        user.setAddress("Test");
        user.setPassword("Test");

        userDao.addUser(user);


    }

    @Override
    public boolean removeUser(int id) {
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}