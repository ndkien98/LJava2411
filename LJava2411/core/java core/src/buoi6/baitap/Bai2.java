package buoi6.baitap;

public class Bai2 {

    public static void main(String[] args) {

        Animal[] animals = new Animal[3];
        Animal lion = new Lion();
        animals[0] = lion;

        Animal elephant = new Elephant();
        animals[1] = elephant;

        Animal monkey = new Monkey();
        animals[2] = monkey;

        for (Animal animal: animals){
            animal.makeSound();
        }

    }
}
