package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.t3h.service.dto.ClaimDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ClaimService {

    BaseResponse<List<ClaimDTO>> getClaims(
            String claimCode, // required = false: có thể truyền vào param hoặc không truyền vào
            LocalDate fromDate,
            LocalDate toDate,
            String codeStatus,
            Pageable pageable
    );
}
