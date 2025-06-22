package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.ClaimDocumentEntity;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.service.FileService;
import vn.com.t3h.service.dto.request.DocumentRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Value("${application.root.folder}")
    private String rootFolder;

    @Value("${application.root.folder.claim-document}")
    private String claimDocumentFolder;

    @Override
    public String getBase64FromPath(String path) {
        if (StringUtils.isEmpty(path)){
            return "";
        }
        String base64 = null;
        // convert file to String base 64
        try {
            File file = new File(path);
            byte[] bytes = Files.readAllBytes(file.toPath());
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

    public List<ClaimDocumentEntity> getClaimDocuments(List<DocumentRequest> documents, ClaimEntity claimEntity) {
        String fullFolderPath = rootFolder + claimDocumentFolder;

        try {
            Files.createDirectories(Paths.get(fullFolderPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ClaimDocumentEntity> claimDocuments = new ArrayList<>();

        for (DocumentRequest doc : documents) {
            try {
                // Lấy phần Base64 (bỏ đi prefix 'data:*/*;base64,')
                String base64Data = doc.getFileBase64Encoded();
                if (base64Data.contains(",")) {
                    base64Data = base64Data.substring(base64Data.indexOf(",") + 1);
                }

                byte[] fileBytes = Base64.getDecoder().decode(base64Data);

                // Lấy phần mở rộng
                String extension = getFileExtensionFromMimeOrName(doc.getDocumentName(), doc.getFileBase64Encoded());

                // Tạo tên file an toàn
                String fileName = claimEntity.getCode() + "_" + doc.getDocumentName().replaceAll("\\s+", "_") + extension;

                File file = new File(fullFolderPath + File.separator + fileName);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(fileBytes);
                }

                // Tạo entity
                ClaimDocumentEntity claimDocumentEntity = new ClaimDocumentEntity();
                claimDocumentEntity.setClaimEntity(claimEntity);
                claimDocumentEntity.setDocumentName(doc.getDocumentName());
                claimDocumentEntity.setUploadDate(LocalDate.now());
                claimDocumentEntity.setCreatedDate(LocalDateTime.now());
                claimDocumentEntity.setFilePath(file.getPath());
                claimDocumentEntity.setDocumentType(extension);

                claimDocuments.add(claimDocumentEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return claimDocuments;
    }


    public String getFileExtensionFromMimeOrName(String fileName, String base64Content) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }

        // Optional: detect from MIME type of base64
        String[] base64Parts = base64Content.split(",");
        if (base64Parts.length > 0 && base64Parts[0].contains("data:")) {
            String mimeType = base64Parts[0].split(";")[0].replace("data:", "");
            switch (mimeType) {
                case "application/pdf":
                    return ".pdf";
                case "image/jpeg":
                    return ".jpg";
                case "image/png":
                    return ".png";
                case "application/msword":
                    return ".doc";
                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                    return ".docx";
                default:
                    return ".bin";
            }
        }
        return ".bin";
    }

}
