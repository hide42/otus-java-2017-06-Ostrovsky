package TestClasses;

public class Cat {
    String name;
    double age;
    Breed breed;
    AdditionalInformation additionalInformation;

    public Cat(String name, double age, Breed breed, AdditionalInformation additionalInformation) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.additionalInformation = additionalInformation;
    }
}
