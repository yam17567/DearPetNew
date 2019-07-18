package com.example.dearpet;

public abstract class Animal {
    public String AnimalId;
    public String username;
    public String password;
    public String AnimalName;
    public String AnimalKind;
    public String AnimalRace;
    public String AnimalGender;
    public String AnimalBirthday;
    public String AnimalLastHaircut;
    public String AnimalLastShower;
    public String AnimalMedications;




    public Animal(String AnimalId,
                  String username,
                  String password,
                  String animalKind ,
                  String AnimalmName ,
                  String animalRace ,
                  String animalGender ,
                  String animalBirthday ,
                  String animalLastHaircut ,
                  String animalLastShower ,
                  String mads)
    {
        this.AnimalId = AnimalId;
        this.username=username;
        this.password=password;
        this.AnimalGender=animalGender;
        this.AnimalKind=animalKind;
        this.AnimalRace=animalRace;
        this.AnimalBirthday=animalBirthday;
        this.AnimalLastHaircut=animalLastHaircut;
        this.AnimalLastShower=animalLastShower;
        this.AnimalMedications =mads;
        this.AnimalName=AnimalmName;
    }

    public String getAnimalId() {
        return AnimalId;
    }

    public void setAnimalId(String animalId) {
        AnimalId = animalId;
    }


    public String getAnimalmName() {
        return AnimalName;
    }


    public String getAnimalKind() {
        return AnimalKind;
    }

    public void setAnimalKind(String animalKind) {
        AnimalKind = animalKind;
    }

    public String getAnimalRace() {
        return AnimalRace;
    }

    public void setAnimalRace(String animalRace) {
        AnimalRace = animalRace;
    }

    public void setAnimalmName(String animalName) {
        AnimalName = animalName;
    }

    public String getAnimalGender() {
        return AnimalGender;
    }

    public void setAnimalGender(String animalGender) {
        AnimalGender = animalGender;
    }

    public String getAnimalBirthday() {
        return AnimalBirthday;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setAnimalBirthday(String animalBirthday) {
        AnimalBirthday = animalBirthday;
    }

    public String getAnimalLastHaircut() {
        return AnimalLastHaircut;
    }

    public void setAnimalLastHaircut(String animalLastHaircut) {
        AnimalLastHaircut = animalLastHaircut;
    }

    public String getAnimalLastShower() {
        return AnimalLastShower;
    }

    public void setAnimalLastShower(String animalLastShower) {
        AnimalLastShower = animalLastShower;
    }

    public String getAnimalMedications() {
        return AnimalMedications;
    }

    public void setAnimalMedications(String animalMedications) {
        this.AnimalMedications = animalMedications;
    }


}
