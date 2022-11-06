/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao.inter;

import entity.EmploymentHistory;
import entity.Skill;
import entity.User;

import java.util.List;

public interface SkillDaoInter {
    public List<Skill> getAll();

    public Skill getById(int id);

    public boolean updateSkill(Skill u);

    public boolean addSkill(Skill u);

    public boolean removeskill(int id);
}
