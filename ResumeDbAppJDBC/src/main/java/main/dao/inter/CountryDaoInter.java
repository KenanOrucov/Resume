/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao.inter;

import entity.Country;
import entity.User;

import java.util.List;

public interface CountryDaoInter {
    public List<Country> getAll();

    public boolean updateCountry(Country u);

    public Country getById(int id);

    public boolean addCountry(Country u);

    public boolean removeCountry(int id);

}
