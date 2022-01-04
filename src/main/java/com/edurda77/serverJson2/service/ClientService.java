package com.edurda77.serverJson2.service;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    String generateJwt(String nameUser, String passwordUser) throws ClassNotFoundException, SQLException;
    void addSendMessage (String jwtKey, String name, String message) throws SQLException, ClassNotFoundException;
    List<String> getLastMessages(String jwtKey, String name, int count) throws SQLException, ClassNotFoundException;
}
