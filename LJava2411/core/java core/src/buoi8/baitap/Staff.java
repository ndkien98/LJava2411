package buoi8.baitap;

public class Staff extends Employee{

    // oTrưởng phòng quản lý (nếu có, nếu không có thì để null).
    public Director truongPhong;

    public Staff(){
        // Khởi tạo đối tượng cha
        super();
        // Khởi tạo giá trị lương của nhân viên đều là 100
        super.setLuongMotNgay(100d);
    }

    @Override
    public double cachTinhLuongThang() {
        return super.getLuongMotNgay() * super.getSoNgayLamViec();
    }

    @Override
    public void nhapThongTin() {
        System.out.println("Nhập thông tin nhân viên: ");
        super.nhapThongTin();
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Hiển thị thông tin nhân viên: ");
        super.hienThiThongTin();
    }

    public Director getTruongPhong() {
        return truongPhong;
    }

    public void setTruongPhong(Director truongPhong) {
        this.truongPhong = truongPhong;
    }
}
