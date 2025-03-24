var response = {
    "code": 200,
    "message": "Success",
    "pageSize": 20,
    "pageIndex": 0,
    "totalPage": 1,
    "totalElement": 7,
    "data": [
        {
            "code": "C001",
            "customerName": "John Doe",
            "nameProduct": "Bảo hiểm xe hơi",
            "claimDate": "2024-04-28",
            "coverageProduct": "Bảo hiểm toàn diện cho xe hơi",
            "statusName": "Khởi tạo"
        },
        {
            "code": "C002",
            "customerName": "Jane Smith",
            "nameProduct": "Bảo hiểm sức khỏe",
            "claimDate": "2023-08-12",
            "coverageProduct": "Bảo hiểm sức khỏe toàn diện",
            "statusName": "Nhân viên đã xác minh"
        },
        {
            "code": "C003",
            "customerName": "Bob Johnson",
            "nameProduct": "Bảo hiểm nhà",
            "claimDate": "2024-07-04",
            "coverageProduct": "Bảo hiểm nhà toàn diện",
            "statusName": "Chập nhận bồi thường"
        },
        {
            "code": "C004",
            "customerName": "John Doe",
            "nameProduct": "Bảo hiểm du lịch",
            "claimDate": "2024-03-30",
            "coverageProduct": "Bảo hiểm du lịch toàn diện",
            "statusName": "Nhân viên đang xử lý"
        },
        {
            "code": "C005",
            "customerName": "Jane Smith",
            "nameProduct": "Bảo hiểm xe hơi",
            "claimDate": "2024-03-23",
            "coverageProduct": "Bảo hiểm toàn diện cho xe hơi",
            "statusName": "Nhân viên đang xử lý"
        },
        {
            "code": "C006",
            "customerName": "John Doe",
            "nameProduct": "Bảo hiểm sức khỏe",
            "claimDate": "2024-03-30",
            "coverageProduct": "Bảo hiểm sức khỏe toàn diện",
            "statusName": "Thông tin chưa chính xác"
        },
        {
            "code": "C007",
            "customerName": "Jane Smith",
            "nameProduct": "Bảo hiểm nhà",
            "claimDate": "2024-03-23",
            "coverageProduct": "Bảo hiểm nhà toàn diện",
            "statusName": "Khởi tạo"
        }
    ]
};

function renderTable(claims){
    // lay ra body cua table
    let bodyTable = document.getElementById('bodyTableData');
    // xóa bỏ toàn bộ dữ liệu đã có của table
    bodyTable.innerHTML = '';
    // chạy 1 vòng for duyệt danh sách claims
    for (let i = 0; i < claims.length; i++) {
        // lấy ra từng claim
        let claim = claims[i];
        // tạo ra từng row
        let rowTable = `<tr>
                                    <td><input type="checkbox" class="recordCheckbox"></td>
                                    <td><strong>${claim.code}</strong></td>
                                    <td>${claim.customerName}</td>
                                    <td>${claim.nameProduct}</td>
                                    <td>${claim.claimDate}</td>
                                    <td>${claim.coverageProduct}</td>
                                    <td><span class="badge bg-label-primary me-1">${claim.statusName}</span></td>
                                </tr>`
        // lắp các row đó vào trong body của table
        bodyTable.innerHTML += rowTable;
    }
}

renderTable(response.data);