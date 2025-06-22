package vn.com.t3h.service.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentRequest {

    private String fileBase64Encoded;
    private String documentName;
    private String documentType;
    private LocalDate updateDate;

}
