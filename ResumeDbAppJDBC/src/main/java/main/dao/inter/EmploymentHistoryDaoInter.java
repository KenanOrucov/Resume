/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao.inter;

import entity.EmploymentHistory;
import entity.User;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

    public EmploymentHistory getById(int id);

    public boolean updateEmploymentHistory(EmploymentHistory u);

    public boolean addEmploymentHistory(EmploymentHistory u);

    public boolean removeEmploymentHistory(int id);

}
