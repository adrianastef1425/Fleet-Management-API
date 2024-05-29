package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import laboratoria.fleet.fleetmanagementapi.repositories.TrajectoriesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class LastTrajectoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajectoriesRepository trajectoriesRepository;

    @Test
    @DisplayName("Test lastTrajectories")
    void validContentSize() throws Exception {
        Pageable pageable = Pageable.ofSize(1).withPage(1);
        List<Trajectories> trajectories = new ArrayList<>();
        Trajectories trajectory= new Trajectories();
        trajectory.setTaxi(new Taxis());
        trajectories.add(trajectory);

        Page<Trajectories> trajectoriesPage = new PageImpl<>(trajectories);
        when(trajectoriesRepository.getAllTaxisWithLastTrajectory(pageable)).thenReturn(trajectoriesPage);

        MvcResult mvcResult = this.mockMvc.perform(get("/trajectories/latest")
                        .param("page", "1")
                        .param("limit", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.length()").value(trajectories.size()))
                .andReturn();
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}