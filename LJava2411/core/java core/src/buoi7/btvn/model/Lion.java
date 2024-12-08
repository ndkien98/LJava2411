package buoi7.btvn.model;

import java.util.Objects;

public class Lion extends Animal {

    @Override
    public void showInfo() {
        System.out.println("Info Lion: ");
        super.showInfo();
    }

    @Override
    public void enterInfo() {
        System.out.println("Enter info Lion: ");
        super.enterInfo();
    }

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


    @Override
    public void action() {
        System.out.println("The lion is hunting.");
    }


}
