package buoi6.baitap;

public class Monkey extends Animal{
    @Override
    public void eat() {
        System.out.println("Monkey is eating");
    }

    @Override
    public void makeSound() {
        System.out.println("Monkey is chattering.");
    }
}
