package buoi6.btvn;

import buoi6.baitap.Animal;
import buoi6.baitap.Lion;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ManagerAnimal  {

    private Animal[] animals;
    private int numberCurrentAnimal;


    public static void main(String[] args) {
        Lion lion1 = new Lion();
        lion1.name = "lion";
        Lion lion2 = new Lion();
        lion2.name = "lion";
        System.out.println(lion1 == lion2);
        System.out.println(lion1.equals(lion2));
        // không chứa các phần tử trùng lặp
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(1);
        integerSet.add(1);

        Set<Lion> lionSet = new HashSet<>();
        lionSet.add(lion1);
        lionSet.add(lion2);
        lionSet.forEach(data -> System.out.println(data.toString()));
    }

}
