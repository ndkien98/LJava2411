const selectedFiles = [];

document.getElementById("submit-claim").addEventListener("click", addClaim);

async function addClaim() {
    const toBase64 = file =>
        new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        });

    // Duyệt toàn bộ file đã lưu
    const documents = [];
    for (const file of selectedFiles) {
        const base64Encoded = await toBase64(file);
        documents.push({
            documentName: file.name,
            documentType: file.type.split('/')[1],
            updateDate: new Date().toISOString().split('T')[0],
            fileBase64Encoded: base64Encoded
        });
    }

    // Tiếp tục như cũ...
    const payload = {
        nameProduct: document.getElementById("insurance-product").value,
        description: document.getElementById("claim-description").value,
        amount: Number(document.getElementById("claim-amount").value),
        claimDate: document.getElementById("claim-date").value,
        customer: {
            name: document.getElementById("customer-name").value,
            email: document.getElementById("customer-email").value,
            phoneNumber: document.getElementById("customer-phone").value,
            address: document.getElementById("customer-address").value,
            bankName: document.getElementById("customer-bank-name").value,
            bankNumber: document.getElementById("customer-bank-number").value
        },
        documents: documents
    };

    try {
        const response = await fetch("http://localhost:8080/api/claim/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "*/*"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) throw new Error(`Server returned ${response.status}`);
        const result = await response.json();
        alert("Yêu cầu đã được gửi thành công!");
        console.log(result);
    } catch (error) {
        console.error("Lỗi khi gửi yêu cầu:", error);
        alert("Gửi yêu cầu thất bại. Vui lòng thử lại.");
    }
}


document.getElementById('file-upload').addEventListener('change', function (event) {
    const fileList = event.target.files;
    const documentList = document.getElementById('document-list');

    Array.from(fileList).forEach(file => {
        // Lưu vào mảng selectedFiles
        selectedFiles.push(file);

        const fileType = file.type.split('/')[1].toUpperCase();
        const uploadDate = new Date().toISOString().split('T')[0];

        const row = document.createElement('tr');

        const fileNameCell = document.createElement('td');
        fileNameCell.textContent = file.name;

        const fileTypeCell = document.createElement('td');
        fileTypeCell.textContent = fileType;

        const uploadDateCell = document.createElement('td');
        uploadDateCell.textContent = uploadDate;

        const actionCell = document.createElement('td');
        const viewButton = document.createElement('button');
        viewButton.className = 'btn btn-brand-2nd';
        viewButton.style.marginRight = '2%';
        viewButton.textContent = 'Xem';
        viewButton.addEventListener('click', () => {
            const url = URL.createObjectURL(file);
            window.open(url, '_blank');
        });

        const deleteButton = document.createElement('button');
        deleteButton.className = 'btn btn-brand-2nd';
        deleteButton.textContent = 'Xóa';
        deleteButton.addEventListener('click', () => {
            row.remove();
            const index = selectedFiles.indexOf(file);
            if (index > -1) {
                selectedFiles.splice(index, 1); // Xoá khỏi mảng khi xóa row
            }
        });

        actionCell.appendChild(viewButton);
        actionCell.appendChild(deleteButton);

        row.appendChild(fileNameCell);
        row.appendChild(fileTypeCell);
        row.appendChild(uploadDateCell);
        row.appendChild(actionCell);

        documentList.appendChild(row);
    });

    // Xóa file input sau khi load để có thể chọn lại file đã chọn
    event.target.value = '';
});
