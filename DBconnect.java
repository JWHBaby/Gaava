package com.example.dm1;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DBconnect {

    private String login;
    private String pass;
    public boolean checkAccount;



    public Connection getDBConnection() {
        String dbName = "accounts";
        String dbUser = "root";
        String dbPass = "";
        String dbServer = "jdbc:mysql://localhost:3306/accounts?useUnicode=true&characterEncoding=UTF-8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbServer, dbUser, dbPass);
        } catch (Exception e) {
            System.out.println("Драйвер на открытие бд недоступен!");
        }
        return null;
    }

    void getlogin(String login) {
        this.login = login;
    }

    void getPass(String pass) {
        this.pass = pass;
    }

    void retdata() throws SQLException {
        try (Connection connection = getDBConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM login WHERE login = ? AND pass = ?")) {
                    statement.setString(1, login);
                    statement.setString(2, pass);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("Пользователь найден!");
                            checkAccount = true;
                        } else {
                            System.out.println("Пользователь не найден.");
                            checkAccount = false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("В sql запросе есть траблы!");
        }
    }
}