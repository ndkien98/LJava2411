package buoi6;

/**
 - bài toán:
    tạo ra class con voi với các thuộc tính:
        tên
        tuổi
        nơi ở
        loại cỏ
        method:
            diChuyen()
            hutNuoc();
    tạo ra class con hổ với các thuộc tính sau
         tên
         tuổi
         nơi ở
         loại thịt
        method:
             diChuyen()
             sanMoi();

 khi class kế thừa từ class Animal:
 + có tất cả các thuộc tính và method mà
 class Animal có
 + có thể truy cập được các thuộc tính và
 method với phạm vi truy cập( public, protected,
 default với điều kiện cùng nằm trong cùng package)
 - Trong Java tất cả các class khi được tạo ra từ thư viện hoặc
 từ dev. Đều mặc định kế thừa class Object của Java
 - This:
 đại diện cho đối tượng hiện tại, để có thể
 sử dụng thao tác với(gọi tới) các thuộc tính,
 method khi con tro nam trong class
 - Supper:
 là 1 biến tham chiếu được sử dụng trong class
 con(subclass) để có thể thao tác(gọi tới) các
 thộc tính, method của lớp cha(supper class)
 gần nhất với lớp con

 - Ghi đè phương thức:
    khi tại lớp con(subclass) có phương thức
    giống y hệ lớp cha(supper class) giống( kiểu dữ liệu
    trả về, tên hàm, tham số truyền vào)
    => lớp con đang thực hiện ghi đè lại phương thức của lớp
    cha

 instanceof: kiểm tra xem object có phải là kiểu của class được chỉ định không(có được tạo ra từ class đ không)
 */
public class Main {

    public static void main(String[] args) {
        // sử dụng class cha khởi tạo đối tượng con
        Voi voi = new Voi();
        voi.diChuyen();

        Ho ho = new Ho();
        ho.diChuyen();
        // Sử dụng class cha, khởi tạo đối tượng con
        Animal animal = new Voi();
//        animal.hutNuoc();
        animal.diChuyen();
        Animal ho2 = new Ho();
        // instanceof: kiểm tra xem object có phải là kiểu của class được chỉ định không(có được tạo ra từ class đ không)
        if (ho2 instanceof Ho){
            ((Ho) ho2).sanMoi();
        }
        ho2.diChuyen();

    }
}
