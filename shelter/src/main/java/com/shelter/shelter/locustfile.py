from locust import HttpUser, task, between

class DogWebsiteUser(HttpUser):
    host = 'http://localhost:8090'
    wait_time = between(1, 5)

    @task
    def my_task(self):
        self.client.get("/dogs")

    @task(5)
    def get_all_dogs(self):
        # Testing the endpoint to retrieve all dogs
        self.client.get("/dogs")

    @task(1)
    def delete_dog(self):
        # Assuming an existing dog ID to delete; you might need to handle IDs dynamically for realistic tests
        self.client.delete("/dogs/delete/1")

    @task(2)
    def get_random_dog_image(self):
        # Fetching a random dog image
        self.client.get("/random-dog-image")

    @task(3)
    def calculate_feeding_cost(self):
        # Calculating feeding cost based on weight
        self.client.get("/feeding-cost", params={"weight": 20})

    @task(3)
    def calculate_required_space(self):
        # Calculating required space based on dog sex
        self.client.get("/required-space", params={"dog_sex": "large"})

    @task(2)
    def predict_weight(self):
        # Predicting weight based on age and breed
        self.client.get("/predict-weight", params={"ageMonths": 24, "breed": "large"})
