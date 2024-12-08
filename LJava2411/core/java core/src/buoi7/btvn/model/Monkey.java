package buoi7.btvn.model;

public class Monkey extends Animal {
    @Override
    public void showInfo() {
        System.out.println("Info Monkey: ");
        super.showInfo();
    }

    @Override
    public void enterInfo() {
        System.out.println("Enter Monkey: ");
        super.enterInfo();
    }

    @Override
    public void eat() {
        System.out.println("Monkey is eating");
    }

    @Override
    public void action() {
        System.out.println("The monkey is climbing trees.");
    }

    @Override
    public void makeSound() {
        System.out.println("Monkey is chattering.");
    }
}
