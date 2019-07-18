package com.example.dearpet;

import java.util.ArrayList;
import java.util.Date;

public class Cat extends Animal
{
    public int CleanSandBoxDay;
    public String wantToCutNails;
    public int eatsInAday;


    public Cat(String AnimalId, String username, String password, String animalKind, String AnimalmName, String animalRace, String animalGender, String animalBirthday, String animalLastHaircut, String animalLastShower, String mads, int cleanSandBoxDay, String wantToCutNails, int eatsInAday) {
        super(AnimalId, username, password, animalKind, AnimalmName, animalRace, animalGender, animalBirthday, animalLastHaircut, animalLastShower, mads);
        CleanSandBoxDay = cleanSandBoxDay;
        this.wantToCutNails = wantToCutNails;
        this.eatsInAday = eatsInAday;
    }

    public int getEatsInAday() {
        return eatsInAday;
    }

    public void setEatsInAday(int eatsInAday) {
        this.eatsInAday = eatsInAday;
    }

    public int getCleanSandBoxDay() {
        return CleanSandBoxDay;
    }

    public void setCleanSandBoxDay(int cleanSandBoxDay) {
        CleanSandBoxDay = cleanSandBoxDay;
    }

    public String isWantToCutNails() {
        return wantToCutNails;
    }

    public void setWantToCutNails(String wantToCutNails) {
        this.wantToCutNails = wantToCutNails;
    }




}
