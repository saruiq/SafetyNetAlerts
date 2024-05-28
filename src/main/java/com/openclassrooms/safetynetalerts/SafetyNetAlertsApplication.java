package com.openclassrooms.safetynetalerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.safetynetalerts.utils.JsonParser;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		
		JsonParser jsonParser = new JsonParser();
		
		try {
			jsonParser.parseJsonToObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
