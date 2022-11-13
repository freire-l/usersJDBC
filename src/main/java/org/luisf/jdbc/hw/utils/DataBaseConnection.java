package org.luisf.jdbc.hw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static String url = "jdbc:mysql://localhost:3306/usuarios_hw?serverTimezone=UTC";
    public static String username = "root";
    public static String pwd = "1234";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url,username,pwd);
        }
        return connection;
    }

}
