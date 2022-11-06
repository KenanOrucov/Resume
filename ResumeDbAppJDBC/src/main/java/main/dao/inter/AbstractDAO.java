/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao.inter;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public Connection connection() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");    // teze kitabxanada buna ehtiyac yoxdur hec
        
        Class.forName("com.mysql.jdbc.Driver");    // teze kitabxanada buna ehtiyac yoxdur hec
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "kenan070";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
