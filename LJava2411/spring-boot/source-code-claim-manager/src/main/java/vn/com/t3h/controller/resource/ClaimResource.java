package vn.com.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.t3h.service.ClaimService;
import vn.com.t3h.service.dto.ClaimDTO;

import java.util.List;

/**
 Đánh dấu đây là 1 controller trả về dữ liệu dạng json cho api
 */
@RestController
@RequestMapping("/api/claim")
public class ClaimResource {

    @Autowired
    private ClaimService claimService;
    // localhost:8080/api/claim
    @GetMapping()
    public ResponseEntity<List<ClaimDTO>> getListClaim(){
        List<ClaimDTO> claimDTOS = claimService.getClaims();
        return ResponseEntity.ok(claimDTOS);
    }
}
