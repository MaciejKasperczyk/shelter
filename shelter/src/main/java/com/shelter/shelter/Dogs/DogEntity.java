package com.shelter.shelter.Dogs;

import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class DogEntity {

    @Id
    @Column(name = "dog_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dog_name")
    private String dog_name;

    @Column(name = "dog_race")
    private String dog_race;

    @Column(name = "dog_sex")
    private String dog_sex;

    @Column(name = "dog_age")
    private int dog_age;

    public DogEntity() {
    }

    public DogEntity(Long id, String dog_name, String dog_race, String dog_sex, int dog_age) {
        this.id = id;
        this.dog_name = dog_name;
        this.dog_race = dog_race;
        this.dog_sex = dog_sex;
        this.dog_age = dog_age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_race() {
        return dog_race;
    }

    public void setDog_race(String dog_race) {
        this.dog_race = dog_race;
    }

    public String getDog_sex() {
        return dog_sex;
    }

    public void setDog_sex(String dog_sex) {
        this.dog_sex = dog_sex;
    }

    public int getDog_age() {
        return dog_age;
    }

    public void setDog_age(int dog_age) {
        this.dog_age = dog_age;
    }
}
