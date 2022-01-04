package com.edurda77.serverJson2;

import com.edurda77.serverJson2.service.DbHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ServerJson2Application {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {


		String key = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXRyb3YiLCJleHAiOjE2NzI3NDQ1NDF9.omFvfJQjTjjr1DK63dwgij51NLvin0-OxbQg-seF_ZI";

		//DbHandler dbHandler = new DbHandler();
		//dbHandler.generateJwt("petrov", "222");
		//dbHandler.addSendMessage(key,"petrov","Hallo");
		SpringApplication.run(ServerJson2Application.class, args);
	}

}
