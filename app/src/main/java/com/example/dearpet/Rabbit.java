package com.example.dearpet;

import java.util.ArrayList;
import java.util.Date;

public class Rabbit extends Animal {
    public int cleanHisCageDay;
    public int mealsAdayR;

    public Rabbit(String AnimalId, String username, String password, String animalKind, String AnimalmName, String animalRace, String animalGender, String animalBirthday, String animalLastHaircut, String animalLastShower, String mads, int cleanHisCageDay, int mealsAdayR) {
        super(AnimalId, username, password, animalKind, AnimalmName, animalRace, animalGender, animalBirthday, animalLastHaircut, animalLastShower, mads);
        this.cleanHisCageDay = cleanHisCageDay;
        this.mealsAdayR = mealsAdayR;
    }

    public int getCleanHisCageDay() {
        return cleanHisCageDay;
    }

    public void setCleanHisCageDay(int cleanHisCageDay) {
        this.cleanHisCageDay = cleanHisCageDay;
    }


    public int getMealsAdayR() {
        return mealsAdayR;
    }

    public void setMealsAdayR(int mealsAdayR) {
        this.mealsAdayR = mealsAdayR;
    }
}

