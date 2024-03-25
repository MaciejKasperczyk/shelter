package com.shelter.shelter.Dogs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DogController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    // Wyswietlenie wszystkich psiaków
    @GetMapping("/dogs")
    public List<Map<String, Object>> getDogs() {
        String sql = "SELECT * FROM dogs";
        List<Map<String, Object>> dogs = jdbcTemplate.queryForList(sql);
        return dogs;
    }
    // Usuwanie psiakow np Invoke-WebRequest -Method Delete -Uri http://localhost:8090/dogs/delete/2  w terminalu
    @DeleteMapping("/dogs/delete/{id}")
    public String deleteDog(@PathVariable Long id) {
        String sql = "DELETE FROM dogs WHERE dog_id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);
        if (deletedRows > 0) {
            return "Dog with id " + id + " has been deleted successfully.";
        } else {
            return "Dog with id " + id + " not found or couldn't be deleted.";
        }
    }

    @GetMapping("/random-dog-image")
    public ResponseEntity<String> getRandomDogImage() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://dog.ceo/api/breeds/image/random";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}