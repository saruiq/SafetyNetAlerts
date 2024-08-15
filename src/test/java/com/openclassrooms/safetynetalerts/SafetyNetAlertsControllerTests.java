package com.openclassrooms.safetynetalerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.openclassrooms.safetynetalerts.controller.SafetyNetAlertsController;
import com.openclassrooms.safetynetalerts.service.FireStationService;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

@SpringBootTest(classes = SafetyNetAlertsController.class)
@AutoConfigureMockMvc
public class SafetyNetAlertsControllerTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FireStationService fireStationService;
	
	@MockBean
	private MedicalRecordService medicalRecordService;
	
	@MockBean
	private PersonService personService;
	
	@MockBean
	private static JsonParser jsonParser;

    @BeforeEach
    public void setup () {
    	
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void listOfPersonsByFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
    }
    

}
