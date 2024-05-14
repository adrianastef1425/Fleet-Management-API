package laboratoria.fleet.fleetmanagementapi;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.repositories.TaxisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc//consultas al controlador interno, para la base de datos ya se contectaba antes

class FleetManagementApiApplicationTests {

    @Autowired//Spring lo inyecte y para poder utilizarlo
    private MockMvc mockMvc;

    @Autowired
    private TaxisRepository taxisRepository;

    @Test
    @DisplayName("Probando el primer endpoint")
    void contextLoads() throws Exception {
        //lo que esta dentro podría devolver un error
        mockMvc.perform(get("/taxis?pageNumber=1&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
