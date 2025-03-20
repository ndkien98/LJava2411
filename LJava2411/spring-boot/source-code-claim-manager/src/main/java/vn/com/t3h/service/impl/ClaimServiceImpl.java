package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.mapper.ClaimMapper;
import vn.com.t3h.repository.ClaimRepository;
import vn.com.t3h.service.ClaimService;
import vn.com.t3h.service.dto.ClaimDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimMapper claimMapper;

    @Override
    public List<ClaimDTO> getClaims() {
        // 5 result entity class
        List<ClaimEntity> claimEntities = claimRepository.findAll();
        // convert data entity -> dto
        List<ClaimDTO> claimDTOs = new ArrayList<ClaimDTO>();
/*
        for (int i = 0; i < claimEntities.size(); i++) {
            ClaimEntity claimEntity = claimEntities.get(i);
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setCode(claimEntity.getCode()); // claim -> code
            claimDTO.setCustomerName(claimEntity.getCustomerEntity().getName()); // customer -> name
            claimDTO.setNameProduct(claimEntity.getInsuranceProductEntity().getName()); // insurance_product -> name
            claimDTO.setClaimDate(claimEntity.getClaimDate());
            claimDTO.setCoverageProduct(claimEntity.getInsuranceProductEntity().getCoverage()); // insurance_product -> coverage
            claimDTO.setStatusName(claimEntity.getClaimStatusEntity().getDescription()); // claim_status -> description
            claimDTOs.add(claimDTO);
        }
 */
        claimDTOs = claimEntities.stream().map(entity -> claimMapper.toDto(entity)).collect(Collectors.toList());
        return claimDTOs;
    }
}
