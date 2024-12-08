package buoi7.btvn;

import buoi7.btvn.model.Animal;
import buoi7.btvn.model.Elephant;
import buoi7.btvn.model.Lion;
import buoi7.btvn.model.Monkey;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter number animal: ");
        int n = new Scanner(System.in).nextInt();
        ManagerAnimal managerAnimal = new ManagerAnimal(n);
        int type = 0;
        do {
            System.out.println("Chọ các chức năng: ");
            System.out.println("1: Thêm mới động vật");
            System.out.println("2: Hiển thị danh sách động vật");
            System.out.println("3: Tìm kiếm con vật theo tên");
            System.out.println("4: Xoá động vật theo tên");
            System.out.println("5: Dừng chương trình");
            type = new Scanner(System.in).nextInt();
            switch (type) {
                case 1:
                    managerAnimal.addNewAnimal();
                    break;
                case 2:
                    managerAnimal.showAllAnimal();
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("Out");
                    break;
                default:
                    System.out.println("Nhập sai số, đề nghị nhập lại");
                    type = 1;
                    break;
            }
        }while (type < 5);
    }
}
