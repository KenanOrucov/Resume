/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;
import dao.impl.*;
import main.dao.inter.*;

public class Context {

    public static UserDaoInter instanceUserDao(){
        return  new UserDaoImpl();
    }


    public static UserSkillDaoInter instanceUserSkillDao(){
        return  new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao(){
        return  new EmploymentHistoryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao(){
        return  new SkillDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao(){
        return  new CountryDaoImpl();
    }
}
