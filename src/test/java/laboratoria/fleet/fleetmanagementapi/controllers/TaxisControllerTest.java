package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.repositories.TaxisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc//consultas al controlador interno, para la base de datos ya se contectaba antes
class TaxisControllerTest {

    @Autowired//Spring lo inyecte y para poder utilizarlo
    private MockMvc mockMvc;

    @Autowired
    private TaxisRepository taxisRepository;

    @Test
    @DisplayName("Test All Taxis")
    void controllerAllTaxisTest() throws Exception {
        //lo que esta dentro podría devolver un error
        MvcResult mvcResult = this.mockMvc.perform(get("/taxis/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(200)))
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Taxis")
    void controllerTaxisTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/taxis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(10)))
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Pages Taxis")
    void controllerPagesTaxisTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/taxis?page=1&limit=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}