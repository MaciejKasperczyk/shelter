package com.shelter.shelter.Dogs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getDogs_ReturnsDogsList() throws Exception {
        String sql = "SELECT * FROM dogs";
        List<Map<String, Object>> mockDogs = new ArrayList<>();
        Map<String, Object> dog = new HashMap<>();
        dog.put("dog_id", 1L);
        dog.put("dog_name", "Rex");
        mockDogs.add(dog);

        given(jdbcTemplate.queryForList(sql)).willReturn(mockDogs);

        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dog_name").value("Rex"));
    }

    @Test
    public void deleteDog_ReturnsSuccessMessage_WhenDogExists() throws Exception {
        Long dogId = 1L;
        String sql = "DELETE FROM dogs WHERE dog_id = ?";
        given(jdbcTemplate.update(sql, dogId)).willReturn(1);

        mockMvc.perform(delete("/dogs/delete/{id}", dogId))
                .andExpect(status().isOk())
                .andExpect(content().string("Dog with id 1 has been deleted successfully."));
    }


}

