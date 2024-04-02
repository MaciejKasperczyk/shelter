![GitHub Actions](https://github.com/MaciejKasperczyk/shelter/actions/workflows/github-actions.yml/badge.svg)

# Getting Started

### Installation

1. Clone repository
2. Install PosgreSQL (https://www.postgresql.org/download/)
3. Create a "dogsdb" database (use 5433 port) and the following user:
name: dogsdb_user
password: 123321
4. Grant access to the "dogsdb" for dogsdb_user.
5. Run "ShelterApplication.java"
6. In PostgreSQL perform following queries:



INSERT INTO users (user_name, user_password, user_type)
VALUES ('Maciej', 'admin', 'admin'),
('Alice', 'password2', 'user'),
('Bob', 'password3', 'user');   

INSERT INTO dogs (dog_age, dog_id, dog_name, dog_race, dog_sex) VALUES
(3, 1, 'Buddy', 'Labrador Retriever', 'Male'),
(5, 2, 'Bailey', 'Golden Retriever', 'Female'),
(2, 3, 'Max', 'German Shepherd', 'Male'),
(4, 4, 'Lucy', 'Beagle', 'Female'),
(1, 5, 'Charlie', 'Bulldog', 'Male');


### Usage

In browser: http://localhost:8090/index.html or http://127.0.0.1:8090/index.html 
