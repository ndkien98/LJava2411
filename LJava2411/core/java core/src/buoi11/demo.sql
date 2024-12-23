-- câu lệnh tạo cơ sở dữ liệu, tạo cơ sở dữ liệu quanlyhocsinh
create database quanlyhocsinh;
-- câu lệnh xóa database, câu lệnh nguy hiểm
drop database quanlyhocsinh;
use quanlyhocsinh; -- chỉ định cho database biết muốn chạy câu lệnh trên database quanlyhocsinh
-- tạo bảng học sinh, để lưu trữ các thông tin của học sinh
create table student ( -- định nghĩa tên bảng, tạo bảng
                         id integer primary key auto_increment, -- tạo ra các cột với cấu trúc (ten_cot kiểu_dữ_liệu), auto_increment id tự động tăng
                         full_name nvarchar(50), -- nvarchar lưu tên tiếng việt
                         date_of_birth date, -- date lưu trữ kiểu dữ liệu date
                         code varchar(50), -- varchar, lưu các ký tự không chứa dấu utf-8,
                         deleted bit, -- lưu trữ giá trị true, false của boolean
                         last_modify_time datetime, -- datetime: dữ liệu dạng ngày tháng có giờ
                         description longtext, -- lưu trữ thông tin 4,294,967,295 characters
    -- avatar blob, -- lưu trữ dạng file
                         point float -- lưu trữ số lẻ
);
-- khóa chính, giúp xác định tính duy nhất độc lập dữ liệu trong bảng, vì hai bảng ghi trong cùng 1 table không được trùng khóa chính với nhau
-- câu lệnh thêm dữ liệu vào bảng
insert into -- câu lệnh thêm dữ liệu
    student -- tên table muốn thêm
(full_name, date_of_birth, code, deleted, description, point) -- các cột muốn thêm dữ liệu
    value -- từ khóa chỉ định đây là các value của cột
    ('Nguyễn Văn Kiên','2021-01-01','S001',0,'Sinh viên quê tại HN',7.5);
insert into -- câu lệnh thêm dữ liệu
    student -- tên table muốn thêm
(full_name, date_of_birth, code, deleted, description, point) -- các cột muốn thêm dữ liệu
    value -- từ khóa chỉ định đây là các value của cột
    ('Nguyễn Văn Kiên','2021-01-01','S001',0,'Sinh viên quê tại HN',7.5);
-- hiển thị toàn bộ dữ liệu trong bảng
select * from student;
-- câu lệnh chỉnh sửa table, thêm cột id làm khóa chính

/*
 bài tập 1
 1) tạo table lớp học với các thông tin
    1) id là khóa chính,
    2) code lưu mã lớp học
    3) ngày mở lớp
    4) deleted lưu trư, false lưu trạng thái lơps bị xóa chưa
    5) description mô tả về lớp học, long text
 2) insert 1 bản ghi dữ liệu cho table bảng lớp học,
 với các lớp học về các ngôn ngữ lập trình
 */




