function getAllClaimFromServer(){
    fetch('http://localhost:8080/api/claim')
        .then(response => {
            if (!response.ok){
                throw new Error('call api error');
            }
            // trả về json data
            return response.json();
        })
        .then(dataResponse => {
            console.log(dataResponse);
            renderTable(dataResponse.data);
        })
}

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

// khi toàn bộ dữ liệu tại window đã load xong
window.onload = function (){
    getAllClaimFromServer();
}