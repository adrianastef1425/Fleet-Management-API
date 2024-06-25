package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.repositories.TaxisRepository;
import laboratoria.fleet.fleetmanagementapi.repositories.TrajectoriesRepository;
import laboratoria.fleet.fleetmanagementapi.services.TrajectoriesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrajectoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxisRepository taxisRepository;

    @Test
    @DisplayName("Test getTrajectoriesController")
    void getTrajectoriesControllerTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/trajectories/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(18142)))
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test getTrajectoriesByIdAndDateController")
    void getTrajectoriesByIdAndDateControllerTest() throws Exception {

        String id = "8935";
        String date = "2008-02-03";
        String page = "0";
        String limit = "10";

        MvcResult mvcResult = this.mockMvc.perform(get("/trajectories")
                        .param("id", id)
                        .param("date", date)
                        .param("page", page)
                        .param("limit", limit))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].taxiId").value("8935"))
                .andExpect(jsonPath("$[9].taxiId").value("8935"))
                .andExpect(jsonPath("$[0].date").value("2008-02-03T05:01:16.000+00:00"))
                .andExpect(jsonPath("$[9].date").value("2008-02-03T05:46:16.000+00:00"))
                .andExpect(jsonPath("$[10]").doesNotExist())
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test getTrajectoriesLastLocationController")
    void getTrajectoriesLastLocationControllerTest() throws Exception {
        String page = "0";
        String limit = "2";

        MvcResult mvcResult = this.mockMvc.perform(get("/trajectories/latest")
                        .param("page", page)
                        .param("limit", limit))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[2]").doesNotExist())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].plate").exists())
                .andExpect(jsonPath("$[0].date").exists())
                .andExpect(jsonPath("$[0].latitude").exists())
                .andExpect(jsonPath("$[0].longitude").exists())
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());

    }
}