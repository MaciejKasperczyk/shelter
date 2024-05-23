package com.shelter.shelter.Dogs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

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
    @GetMapping("/dogs/{id}/last-visit")
    public String getLastVetVisit(@PathVariable String id) {
        LocalDate randomDate = getRandomDate();
        return "" +randomDate;
    }

    private LocalDate getRandomDate() {
        long minDay = LocalDate.now().minusDays(365).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }



        @GetMapping("/feeding-cost")
        public ResponseEntity<Double> calculateFeedingCost(@RequestParam double weight) {
            double cost = weight * 10;
            return ResponseEntity.ok(cost);
        }


        @GetMapping("/required-space")
        public ResponseEntity<Double> calculateRequiredSpace(@RequestParam String dog_sex){
            double space = "large".equals(dog_sex) ? 30.0 : 15.0; // 30 square meters for large breeds, 15 for others
            return ResponseEntity.ok(space);
        }

        @GetMapping("/predict-weight")
        public ResponseEntity<Double> predictAdultWeight(@RequestParam int ageMonths, @RequestParam String breed) {
            double baseWeight = "large".equals(breed) ? 50.0 : 20.0; // Base adult weight for different sizes
            double predictedWeight = baseWeight * (1 + (ageMonths / 12.0 * 0.1)); // Increase weight by 10% per year
            return ResponseEntity.ok(predictedWeight);
        }


}