/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao.inter;

import entity.User;
import entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserSkillId(int userId);
    
    public UserSkill getById(int id);

    public boolean updateUserSkill(UserSkill u);

    public boolean addUserSkill(UserSkill u);

    public boolean removeUserSkill(int id);

}

