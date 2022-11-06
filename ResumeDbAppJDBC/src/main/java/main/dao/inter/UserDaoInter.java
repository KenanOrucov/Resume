package main.dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User findByEmailAndPassword(String email, String password);

    public User findByEmail(String email);

    public User getById(int id);

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);

}
