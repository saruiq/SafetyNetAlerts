package com.openclassrooms.safetynetalerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.controller.SafetyNetAlertsController;
import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.model.PersonsProfile;
import com.openclassrooms.safetynetalerts.service.FireStationService;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

@SpringBootTest(classes = SafetyNetAlertsController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class SafetyNetAlertsControllerTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean
//	private FireStationService fireStationService;
//	
//	@MockBean
//	private MedicalRecordService medicalRecordService;
//	
//	@MockBean
//	private PersonService personService;
//	
//	@Mock
//    PersonsProfile personsProfile = new PersonsProfile();
//	

    @BeforeEach
    public void setup () {
    	
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		try {
			JsonParser.parseJsonToObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void listOfPersonsByFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
    }
    
    @Test
    void getListOfPhoneNumbersByFireStationNumberTest() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());
    }
   
    
    @Test
    void getEmailAddressesFromCityTest() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk());
    }
    
    @Test
    void getFireStationNumberAndPersonsFromAddressTest() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk());
    }
    
    @Test
    void getChildrenByAddressTest() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
    }
    
    @Test
    void getPersonInfoTest() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd")).andExpect(status().isOk());
    }
    
    @Test
    void getHouseHoldInfoFromStationNumbersTest() throws Exception {
        mockMvc.perform(get("/flood?stations=1,2,3")).andExpect(status().isOk());
    }
    
    @Test
    void createPersonsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/person")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void updatePersonsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/person")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void deletePersonsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/person")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void createFireStationsTest() throws Exception {
    	FireStations fireStation = new FireStations("1234 Birmingham St.", "1"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/fireStation")
      	      .content(asJsonString(fireStation))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void updateFireStationsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/fireStation")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void deleteFireStationsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/fireStation")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void createMedicalRecordsTest() throws Exception {
    	MedicalRecords medicalRecord = new MedicalRecords("Sarah", "Iqbal", "01/27/2000", null , null); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/medicalRecord")
      	      .content(asJsonString(medicalRecord))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void updateMedicalRecordsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/medicalRecord")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
    
    @Test
    void deleteMedicalRecordsTest() throws Exception {
    	Persons person = new Persons("Sarah", "Iqbal", "1234 Birmingham St.", "Naperville", "18467", "348-948-1836", "orange@email.com"); 
        mockMvc.perform( MockMvcRequestBuilders
      	      .post("/medicalRecord")
      	      .content(asJsonString(person))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
          
    }
   
     
  
    

}
