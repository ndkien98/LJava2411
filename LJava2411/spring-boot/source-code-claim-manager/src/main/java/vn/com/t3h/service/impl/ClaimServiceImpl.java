package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.*;
import vn.com.t3h.mapper.ClaimMapper;
import vn.com.t3h.repository.ClaimRepository;
import vn.com.t3h.repository.ClaimStatusRepository;
import vn.com.t3h.repository.CustomerEntityRepository;
import vn.com.t3h.repository.InsuranceProductRepository;
import vn.com.t3h.service.ClaimService;
import vn.com.t3h.service.FileService;
import vn.com.t3h.service.dto.ClaimDTO;
import vn.com.t3h.service.dto.request.ClaimRequest;
import vn.com.t3h.service.dto.request.CustomerRequest;
import vn.com.t3h.service.dto.request.DocumentRequest;
import vn.com.t3h.service.dto.response.Response;
import vn.com.t3h.service.dto.response.ResponsePage;
import vn.com.t3h.utils.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {



    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private InsuranceProductRepository insuranceProductRepository;

    @Autowired
    private ClaimStatusRepository claimStatusRepository;

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;


    public Response<String> createClaim(ClaimRequest claimRequest) {

        // create claim Entity
        ClaimEntity claimEntity = new ClaimEntity();
        claimEntity.setAmount(claimRequest.getAmount());
        claimEntity.setClaimDate(claimRequest.getClaimDate());
        Long totalClaim = claimRepository.count();
        claimEntity.setCode(Constant.createCodeClaim(totalClaim));
        claimEntity.setDescription(claimRequest.getDescription());
        claimEntity.setCreatedBy(Constant.ROLE_ADMIN_CODE);
        claimEntity.setLastModifiedBy(Constant.ROLE_ADMIN_CODE);

        // create customer Entity
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerRequest customerRequest = claimRequest.getCustomer();
        customerEntity.setName(customerRequest.getName());
        customerEntity.setPhoneNumber(customerRequest.getPhoneNumber());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setAddress(customerRequest.getAddress());

        // create insurance product Entity
        InsuranceProductEntity insuranceProductEntity = insuranceProductRepository.findByName(claimRequest.getNameProduct());
        claimEntity.setInsuranceProductEntity(insuranceProductEntity);

        // create status
        ClaimStatusEntity claimStatusEntity = claimStatusRepository.findByCode(Constant.CLAIM_STATUS.NEW.name());
        claimEntity.setClaimStatusEntity(claimStatusEntity);

        List<ClaimDocumentEntity> claimDocumentEntityList = fileService.getClaimDocuments(claimRequest.getDocuments(),claimEntity);

        claimEntity.setClaimDocumentEntities(claimDocumentEntityList);
        claimEntity.setCustomerEntity(customerEntity);
        customerEntity.setClaims(Arrays.asList(claimEntity));
        customerEntityRepository.save(customerEntity);

        Response<String> response = new Response();
        response.setMessage("claim created successfully with code " + claimEntity.getCode());
        response.setData(claimEntity.getCode());
        return response;
    }

    @Override
    public ResponsePage<List<ClaimDTO>> getClaims(String claimCode,
                                                  LocalDate fromDate,
                                                  LocalDate toDate,
                                                  String codeStatus,
                                                  Pageable pageable) {
        // 5 result entity class
        if (StringUtils.isEmpty(claimCode)){
            claimCode = null;
        }
        if (StringUtils.isEmpty(codeStatus)){
            codeStatus = null;
        }
        Page<ClaimEntity> pageEntity = claimRepository.findCondition(claimCode,fromDate,toDate,codeStatus,pageable);
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
        claimDTOs = pageEntity.map(entity -> claimMapper.toDto(entity)).stream().toList();
        ResponsePage<List<ClaimDTO>> response = new ResponsePage<>();
        response.setData(claimDTOs);
        response.setMessage("Success");
        response.setCode(HttpStatus.OK.value()); // 200
        response.setTotalElement(pageEntity.getTotalElements());
        response.setTotalPage(pageEntity.getTotalPages());
        response.setPageSize(pageable.getPageSize());
        response.setPageIndex(pageable.getPageNumber());
        return response;
    }



}
