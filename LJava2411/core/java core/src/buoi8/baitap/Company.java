package buoi8.baitap;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

public class Company {

    private String tenCongTy;
    private String maSoThue;
    private Double doanhThuThang;

    private List<Employee> danhSachNhanVien = new ArrayList<>();

    public void nhapThongTinCongTy(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên công ty: ");
        this.tenCongTy = scanner.nextLine();
        System.out.println("Nhập mã số thuế: ");
        this.maSoThue = scanner.nextLine();
        System.out.println("Nhập doanh thu tháng: ");
        this.doanhThuThang = scanner.nextDouble();
    }

    public void hienThiToanBoNhanVien(){
        System.out.println();
        System.out.println("=======Thông tin toàn bộ nhân viên trong công ty======");
        for(Employee employee : danhSachNhanVien){
            employee.hienThiThongTin();
        }
        System.out.println();
    }

    /**
     3.	Thêm, xóa thông tin một nhân sự
     o	Thêm: Cho phép thêm thông tin của một nhân viên mới hoặc trưởng phòng mới vào hệ thống.
     	Các thông tin cần nhập: Mã số nhân viên, họ tên, số điện thoại, số ngày làm việc, lương 1 ngày, chức vụ (giám đốc, trưởng phòng, nhân viên).
     */
    public void themNhaSu(){
        System.out.println("Thêm một nhân sự ");
        Employee employee = chonLoaiNhanVien();
        if (employee == null){
            return;
        }
        employee.nhapThongTin();
        danhSachNhanVien.add(employee);
    }

    private Employee chonLoaiNhanVien() {
        Employee employee = null;
        int luaChon= 0;
        System.out.println("Chọn chức vụ muốn thêm: ");
        System.out.println("1: Giám đốc");
        System.out.println("2: Quản lý");
        System.out.println("3: Nhân viên");
        System.out.println("4: Hủy");
        luaChon = new Scanner(System.in).nextInt();
        switch (luaChon){
            case 1:
                employee = new Director();
                break;
            case 2:
                employee = new Manager();
                break;
            case 3:
                employee = new Staff();
                break;
            case 4:
                System.out.println("Thoát");
                break;
            default:
                System.out.println("Không hợp lệ, đề nghị nhập lại");
                break;
        }

        return employee;
    }
    /*
  o	Xóa: Cho phép xóa nhân viên hoặc trưởng phòng khỏi hệ thống.
     	Khi xóa trưởng phòng, cần phải ngắt liên kết giữa trưởng phòng đó và các nhân viên dưới quyền của họ.
     	Các nhân viên bị xóa trưởng phòng cần phải cập nhật lại thông tin trưởng phòng (có thể để là null nếu không có trưởng phòng).
     giải:
     b1) Nhập vào mã nhân viên muốn xóa:
     b2) Tìm nhân viên muốn xóa
     b3) nếu tìm thấy nhân viên cần xóa tiếp tục kiểm tra các trường hợp
          nếu
               == giám đốc => sử dụng hàm remove để xóa
               == nhân viên => sử dụng hàm remove để xóa
               == quản lý => trước khi xóa trường phòng
                            b1: Tìm hết danh sách nhân viên đang trực thuốc trưởng phòng đó
                            b2: Set toàn bộ thuộc tính trường phòng của nhân đo == null
                                sử dụng method: setTruongPhong(null)
                                => để giải phóng nhân viên đó khỏi trưởng phòng đã bị xóa
     */

    public void xoaNhanVien(){
        // b1 Nhập vào mã nhân viên muốn xóa:
        System.out.println("Nhập vào mã nhân viên: ");
        String maMvXoa = new Scanner(System.in).nextLine();
        // b2 tim kiem
        Employee employeeDelete = timKiemNhanVien(maMvXoa);
        if (employeeDelete == null){
            System.out.println("Không tìm thấy nhân viên với mã: " + maMvXoa);
            return;
        }
        // b3 xóa
        if (employeeDelete instanceof Director || employeeDelete instanceof Staff){
            this.danhSachNhanVien.remove(employeeDelete);
        }else if (employeeDelete instanceof Manager){
            xoaTruongPhongKhoiNhanVien(maMvXoa);
            // nếu là instanceof Manager => ép kiểu biến employeeDelete thành đối tượng kiểu Manager
            this.danhSachNhanVien.remove(employeeDelete);
        }
        System.out.println("Xóa nhân viên thành công");
    }

    /**
     2.	Phân bổ nhân viên vào trưởng phòng
     o	Cho phép nhập 1 mã nhân viên và mã trường phòng để thêm nhân viên đó vào cho trưởng phòng đó. Khi thêm sẽ update cả thông tin trưởng phòng quản lý cho nhân viên, và danh sách nhân viên của trưởng phòng
     o	Điều kiện: Mỗi nhân viên sẽ có 1 trưởng phòng quản lý (trừ giám đốc). Trưởng phòng sẽ quản lý một hoặc nhiều nhân viên.
     o	Cập nhật số lượng nhân viên dưới quyền cho trưởng phòng.
     giai:
        1) Phải biết đưa nhân viên nào ?, => yêu cầu nhập mã nhân viên
        2) Nhân viên đấy có tồn tại trong công ty không ? => kiểm tra xem có tồn tại nhân viên trong danhSachNhanVien không, nếu có => trả ra nhân viên
        3) Nhân viên đấy đã được đưa vào phòng nào chưa ? => kiểm tra thuộc tính truongPhong của nhân viên đấy có bằng null không, nếu == null => hợp lệ, ngược lại
        4) Đưa nhân viên đấy vào quản lý nào ? => yêu cầu nhập vào mã nhân viên của quản lý
        5) quản lý đấy có tồn tại trong hệ thống khôg ? => kiểm tra xem có tồn tại quản lý đó trong danhSachNhanVien không, nếu có => trả ra quản lý

        sau khi trả lơif được các câu hỏi này bắt đầu đưa nhân viên đó vào phòng
            b1) Thêm nhân viên đó vào danh sách nhân viên của quản lý bằng cách setQuanLy = quanLy đã tìm được ở trên
            b2) Thêm nhân viên đó vào trong danhSachNhanVien của quản lý
     */
    public void phanBoNhanVienVaoPhong(){
        System.out.println("Nhập vào mã nhân viên muốn đưa vào cho quản lý: ");
        String maNV = new Scanner(System.in).nextLine();
        // kiểm tra xem có tồn tại nhân viên trong danhSachNhanVien không, hoặc có tồn tại mã nhân viên đó, nhưng đó không phải là staff
        Employee employee = timKiemNhanVien(maNV);
        if (!(employee instanceof Staff)){
            System.out.println("Không tồn tại nhân viên với mã: " + maNV);
            return;
        }

        Staff staff = ((Staff) employee); // ép kiểu employee -> staff
        // Nhân viên đấy đã được đưa vào phòng nào chưa ?
        if (staff.getTruongPhong() != null){
            System.out.println("Nhân viên " + maNV + " đã được đưa vào cho quản lý " + staff.getTruongPhong().getMaMV());
            return;
        }
        System.out.println("Nhập vào mã nhân viên của quản lý: ");
        String maNVQuanLy = new Scanner(System.in).nextLine();
        Employee employeeManager = timKiemNhanVien(maNVQuanLy);
        // kiểm tra xem có tồn tại quản lý trong danhSachNhanVien không, hoặc có tồn tại mã nhân viên đó, nhưng đó không phải là quản lý
        if (!(employeeManager instanceof Manager)){
            System.out.println("Không tồn tại nhân viên với mã: " + maNV);
            return;
        }
        Manager manager = ((Manager) employeeManager);
        // Set quản lý cho nhân viên
        staff.setTruongPhong(manager);
        // Thêm nhân viên vào cho quản lý
        List<Staff> staffManager = manager.getDanhSachNhanVien();
        staffManager.add(staff);
    }

    private void xoaTruongPhongKhoiNhanVien(String maMvXoa) {
        // Lấy danh sách nhân viên trong manager, và set null director
        for (Employee employee: this.danhSachNhanVien){
            // Kiểm tra nếu không phải nhân viên => bỏ qua
            if (employee instanceof Director || employee instanceof Manager){
                continue;
            }
            // Nếu là nhân viên kiểm tra có thuộc về giám đốc không, ép kiểu về kiểu nhân viên
            Staff staff = ((Staff) employee);
            if (staff.getTruongPhong() != null && staff.getTruongPhong().getMaMV().equalsIgnoreCase(maMvXoa)){
                staff.setTruongPhong(null);
            }
        }
    }

    public Employee timKiemNhanVien(String maNV){
        for (Employee employee:this.danhSachNhanVien){
            if (maNV.equalsIgnoreCase(employee.getMaMV())){
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<Employee> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public Double getDoanhThuThang() {
        return doanhThuThang;
    }

    public void setDoanhThuThang(Double doanhThuThang) {
        this.doanhThuThang = doanhThuThang;
    }
}
