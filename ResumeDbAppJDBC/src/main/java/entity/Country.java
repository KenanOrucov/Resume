/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.util.Objects;

public class Country {
    private int id;
    private String name;
    private String nationality;

    public Country() {
    }

    public Country(int id, String name, String countryName) {
        this.id = id;
        this.name = name;
        this.nationality = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(nationality, country.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationality);
    }

    @Override
    public String toString() {
        return name +  " (" + nationality + ")";
    }

}

