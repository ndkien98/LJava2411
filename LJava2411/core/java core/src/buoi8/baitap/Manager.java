package buoi8.baitap;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Manager extends Employee{

    // Khởi tạo mặc định cho list
    private List<Staff> danhSachNhanVien = new ArrayList<>();

    public Manager(){
        super();
        super.setLuongMotNgay(200d);
    }

    @Override
    public double cachTinhLuongThang() {
        return super.getLuongMotNgay() * super.getSoNgayLamViec() + (100 * danhSachNhanVien.size());
    }

    @Override
    public void nhapThongTin() {
        System.out.println("Nhập thông tin quản lý: ");
        super.nhapThongTin();
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Hiển thị thông tin quản lý: ");
        super.hienThiThongTin();
    }

    public List<Staff> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<Staff> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }
}
