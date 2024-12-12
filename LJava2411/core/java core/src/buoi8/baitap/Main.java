package buoi8.baitap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        int type = 0;
        do {
            System.out.println("Nhập các chức năng: ");
            System.out.println("1: Nhap thong tin cong ty");
            System.out.println("2: Phân bổ nhân viên vào trưởng phòng");
            System.out.println("3: Thêm, một nhân sự");
            System.out.println("4: xóa thông tin mot nhat su");
            System.out.println("5: Hien thi danh sach nhan vien");
            System.out.println("6: Thoat");
            type = new Scanner(System.in).nextInt();
            switch (type) {
                case 1:
                    company.nhapThongTinCongTy();
                    break;
                case 2:
                    company.phanBoNhanVienVaoPhong();
                    break;
                case 3:
                    company.themNhaSu();
                    break;
                case 4:
                    company.xoaNhanVien();
                    break;
                case 5:
                    company.hienThiToanBoNhanVien();
                    break;
                case 6:
                    System.out.println("Thoat!");
                    break;
            }
        }while (type != 6);
    }
}
