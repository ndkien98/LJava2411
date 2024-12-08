package buoi7.btvn.model;

public class Elephant extends Animal {


    @Override
    public void showInfo() {
        System.out.println("Info Elephant: ");
        super.showInfo();
    }

    @Override
    public void enterInfo() {
        System.out.println("Enter Elephant: ");
        super.enterInfo();
    }

    public void trumpet(){
        System.out.println("Elephant is trumpeting");
    }

    @Override
    public void eat() {
        System.out.println("Elephant is eating");
    }

    @Override
    public void action() {
        System.out.println("The elephant is spraying water.");
    }

    @Override
    public void makeSound() {
        System.out.println("Elephant is trumpeting.");
    }
}
