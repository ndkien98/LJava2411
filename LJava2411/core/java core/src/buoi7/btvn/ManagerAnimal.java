package buoi7.btvn;

import buoi7.btvn.model.Animal;
import buoi7.btvn.model.Elephant;
import buoi7.btvn.model.Lion;
import buoi7.btvn.model.Monkey;

import java.util.Scanner;

public class ManagerAnimal {

    private Animal[] animals;
    private int currentAnimalIndex = 0;
    private int totalAnimals = 0;
    public ManagerAnimal(int totalAnimals) {
        this.totalAnimals = totalAnimals;
        animals = new Animal[totalAnimals];
    }

    public void initAnimal(){
        for (int i = 0; i < totalAnimals; i++) {

        }
    }

    public void addNewAnimal() {
        int typeAnimal = 0;
        do {
            System.out.println("Lựa chọn nhập loại vật: ");
            System.out.println("1: Lion");
            System.out.println("2: Elephant");
            System.out.println("3: Monkey");
            System.out.println("4: Dừng nhập");
            typeAnimal = new Scanner(System.in).nextInt();
            Animal animal = null;
            switch (typeAnimal) {
                case 1:
                    animal = new Lion();
                    break;
                case 2:
                    animal = new Elephant();
                    break;
                case 3:
                    animal = new Monkey();
                    break;
                case 4:
                    System.out.println("Bạn đã chọn dừng nhập");
                    break;
                default:
                    System.out.println("Nhập sai số, đề nghị nhập lại");
                    typeAnimal = 1;
                    break;
            }
            if (animal != null){
                animal.enterInfo();
                animals[currentAnimalIndex] = animal;
                currentAnimalIndex++;
                System.out.println("Thêm động vật thứ " + currentAnimalIndex + " thành công");
            }
        }while (typeAnimal <= 3);
    }
}
