package com.example.dearpet;

public class Dog extends Animal
{
    public int numberOfTrips;
    public int mealsAday;
    public String commands;


    public Dog(String id , String AnimalId , String username , String password , String animalKind , String AnimalmName , String animalRace , String animalGender , String animalBirthday , String animalLastHaircut , String animalLastShower , String mads , int numberOfTrips , int mealsAday , String commands) {
        super(AnimalId, username, password, animalKind, AnimalmName, animalRace, animalGender, animalBirthday, animalLastHaircut, animalLastShower, mads);

        this.numberOfTrips = numberOfTrips;
        this.mealsAday = mealsAday;
        this.commands = commands;
    }

    public int getMealsAday() {
        return mealsAday;
    }

    public void setMealsAday(int mealsAday) {
        this.mealsAday = mealsAday;
    }


    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }





}




