package vn.com.t3h.service;

import vn.com.t3h.entity.ClaimDocumentEntity;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.service.dto.request.DocumentRequest;

import java.util.List;

public interface FileService {

    String getBase64FromPath(String path);

    public List<ClaimDocumentEntity> getClaimDocuments(List<DocumentRequest> documents, ClaimEntity claimEntity);
}
