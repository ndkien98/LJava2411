package buoi6.baitap;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lion){
            return ((Lion) obj).name.equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
