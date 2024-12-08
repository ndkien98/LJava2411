package buoi7.btvn.model;

import java.util.Scanner;

/*
abstract: đánh dấu là class hoặc method trìu tượng
    - class abstract:
         + có các thuộc tính, khối lệnh như class thông thường
         + có các method trìu tượng: là các method chỉ có phần
            khai báo tên hàm, kiểu dữ liệu trả về, tham số
            và không có phần thân hàm
            vd: public abstract void eat();
            phần thaan hàm sẽ được class subclass triển khai
            lại theo các cách riêng, phù hợp với đặc tính
         + luôn là class cha(supper class) để cho các class
            class con kế thừa
         + không thể sử constructor để khởi tạo đối tượng,
         bắt buộc phải sử dụng constructor của subclass để khởi
         tạo đối tượng

 */
public abstract class Animal {
    private String name;
    private int age;

    public abstract void eat();
    public abstract void action();
    public abstract void makeSound();
    public void run(){
        System.out.println("Animal running");
    }
    public void showInfo(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public void enterInfo(){
        System.out.println("Enter name: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Enter age: ");
        this.age = new Scanner(System.in).nextInt();
    }

    public int run(String ten){
        System.out.println(ten + " is running");
        return 0;
    }

    public int run(String ten1,String ten2){
        return 0;
    }
}
