package buoi6.baitap;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Bai1 {

    public static void main(String[] args) {

        Lion lion = new Lion();
        Elephant elephant = new Elephant();
        lion.eat();
        elephant.eat();
        lion.roar();
        elephant.trumpet();
        Animal tiger = new Animal() {
            @Override
            public void eat() {
                System.out.println("tiger is eating");
            }

            @Override
            public void makeSound() {

            }
        };
        tiger.eat();
    }
}
