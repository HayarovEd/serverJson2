package com.edurda77.serverJson2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
//@RequestMapping("message")
public class ClientController {
  /*  @GetMapping
    public String list() {
        return "debil";
    }*/

    @Autowired
    DbHandler dbHandler;

    /*@PostMapping("/greeting")
    public String generateJwt(@RequestBody model.Model model)
            throws SQLException, ClassNotFoundException {
        return dbHandler.generateJwt(model.getName(), model.getPassword());
    }*/
    @PostMapping("/greeting")
    public model.Model generateJwt(@RequestBody model.Model model)
            throws SQLException, ClassNotFoundException {
        String key = dbHandler.generateJwt(model.getName(),model.getPassword());
        model.setJwt(key);
        return new model.Model(key);}

}
