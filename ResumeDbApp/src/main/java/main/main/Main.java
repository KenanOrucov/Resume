/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.main;

import context.Context;
import entity.Skill;
import main.dao.inter.CountryDaoInter;
import main.dao.inter.SkillDaoInter;
import main.dao.inter.UserDaoInter;

public class Main {

    public static void main(String[] args) throws Exception {
     //   CountryDaoInter dao = Context.instanceCountryDao();

     //   System.out.println(dao.getAll());

        SkillDaoInter skillDao = Context.instanceSkillDao();
        Skill s = new Skill(11, "pul");
            boolean b = skillDao.addSkill(s);
            System.out.println(b);
      
    }


}
