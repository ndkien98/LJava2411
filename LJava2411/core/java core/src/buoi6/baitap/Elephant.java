package buoi6.baitap;

public class Elephant extends Animal{

    public void trumpet(){
        System.out.println("Elephant is trumpeting");
    }

    @Override
    public void eat() {
        System.out.println("Elephant is eating");
    }

    @Override
    public void makeSound() {
        System.out.println("Elephant is trumpeting.");
    }
}
