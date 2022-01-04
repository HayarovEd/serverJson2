package com.edurda77.serverJson2.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbHandler implements ClientService {
    private static final String CON_STR = "jdbc:sqlite:server_json.db";
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    JwtService jwtService = new JwtService();

    @Override
    public String generateJwt(String nameUser, String passwordUser) throws ClassNotFoundException, SQLException {
        String jwtToken;
        statmt = statmt();
        resSet = statmt.executeQuery("SELECT * FROM users");
        boolean check = false;
        while (resSet.next()) {
            String user = resSet.getString("user");
            String password = resSet.getString("password");
            if (user.equals(nameUser) && password.equals(passwordUser)) {
                check = true;
            }

        }
        if (check) {
            jwtToken = jwtService.generateToken(nameUser);
            statmt.executeUpdate("UPDATE users SET jwtKey = '" + jwtToken + "' WHERE user='" + nameUser + "'");
            System.out.println(nameUser + " - " + jwtToken);
        } else {
            jwtToken = "Ошибка";
            System.out.println("пароль или имя неверны");
        }
        return jwtToken;
    }

    @Override
    public void addSendMessage(String jwtKey, String name, String message)
            throws SQLException, ClassNotFoundException {
        statmt = statmt();
        resSet = statmt.executeQuery("SELECT * FROM users WHERE user='" + name + "'");
        String jwtKeyFromBd = resSet.getString("jwtKey");
        if (jwtService.validateAccessToken(jwtKeyFromBd)) {
            if (jwtKey.equals(jwtKeyFromBd)) {
                statmt.executeUpdate("INSERT INTO messages (user, message) VALUES ('" + name + "','" + message + "')");
                System.out.println("запись:  " + message + "  добавлена");
            }
        }
    }

    @Override
    public List<String> getLastMessages(String jwtKey, String name, int count)
            throws SQLException, ClassNotFoundException {
        ArrayList<String> lastMessages = new ArrayList<>();
        statmt = statmt();
        resSet = statmt.executeQuery("SELECT * FROM messages ORDER BY id DESC LIMIT '" + count + "'");
        while (resSet.next()) {
            String message = resSet.getString("message");
            lastMessages.add(message);
        }
        return lastMessages;
    }

    private Statement statmt() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(CON_STR);
        statmt = conn.createStatement();
        return statmt;
    }

}
