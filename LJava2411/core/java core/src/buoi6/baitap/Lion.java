package buoi6.baitap;

public class Lion extends Animal {

    public void roar(){
        System.out.println("Lion is roaring");
    }

    @Override
    public void eat() {
        System.out.println("Lion is eating");
    }

    @Override
    public void makeSound() {
        System.out.println("Lion is roaring.");
    }
}
