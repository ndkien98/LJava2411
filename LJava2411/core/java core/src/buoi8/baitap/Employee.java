package buoi8.baitap;

import java.util.Scanner;

public abstract class Employee {

    /*
    Double,Integer,Long,Boolean
        + chứa được giá trị null vì nó là kiểu
            dữ liệu đối tượng
        + khởi tạo đối tượng trong heap như 1 đối
            tượng bình thường
    double,long,boolean,int...
        + không chứa được giá trị null
        + chỉ lưu giá trị trong stack
     */
    private String maMV;
    private String hoTen;
    private String sdt;
    private Integer soNgayLamViec;
    private Double luongMotNgay;

    public void nhapThongTin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap thong tin MaNV: ");
        this.maMV = scanner.nextLine();
        System.out.println("Nhap thong tin HoTen: ");
        this.hoTen = scanner.nextLine();
        System.out.println("Nhap thong tin SDT: ");
        this.sdt = scanner.nextLine();
        System.out.println("Nhap thong tin So Ngay Lam Viec: ");
        this.soNgayLamViec = scanner.nextInt();
//        System.out.println("Nhap thong tin Luong Mot Ngay: ");
//        this.luongMotNgay = scanner.nextDouble();
    }

    public void hienThiThongTin(){
        System.out.println("MaNV : " + maMV);
        System.out.println("HoTen : " + hoTen);
        System.out.println("SDT : " + sdt);
        System.out.println("SoNgayLamViec : " + soNgayLamViec);
        System.out.println("LuongMotNgay : " + luongMotNgay);
    }


    public abstract double cachTinhLuongThang();

    public String getMaMV() {
        return maMV;
    }

    public void setMaMV(String maMV) {
        this.maMV = maMV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getSoNgayLamViec() {
        return soNgayLamViec;
    }

    public void setSoNgayLamViec(Integer soNgayLamViec) {
        this.soNgayLamViec = soNgayLamViec;
    }

    public Double getLuongMotNgay() {
        return luongMotNgay;
    }

    public void setLuongMotNgay(Double luongMotNgay) {
        this.luongMotNgay = luongMotNgay;
    }
}
