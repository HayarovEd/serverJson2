package com.edurda77.serverJson2.service;


import com.edurda77.serverJson2.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {


    @Autowired
    DbHandler dbHandler;


    @PostMapping("/generateJwt")
    public String generateJwt(@RequestBody Model model)
            throws SQLException, ClassNotFoundException {
        String key = dbHandler.generateJwt(model.getName(), model.getPassword());
        model.setJwt(key);
        return "Token: "+key;
    }
    @PutMapping ("/addMessage")
    public void addMessage (@RequestBody Model model) throws SQLException, ClassNotFoundException {
        dbHandler.addSendMessage(model.getJwt(), model.getName(), model.getMessage());
    }
    @GetMapping ("/getMessages")
    public List<String> getMessages (@RequestBody Model model) throws SQLException, ClassNotFoundException {
        int count = Integer.parseInt(model.getMessage().substring(8));
        return  dbHandler.getLastMessages(model.getJwt(), model.getName(), count);
    }
}
