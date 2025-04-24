//package com.t3h.service.impl;
//
//import com.t3h.config.ApplicationPropertiesConfig;
//import com.t3h.model.response.BaseResponse;
//import com.t3h.service.FileService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service
//@Slf4j
//public class FileServiceImpl implements FileService {
//
//
//    @Autowired
//    private ApplicationPropertiesConfig propertiesConfig;
//
//    @Override
//    public BaseResponse uploadFileLocal(MultipartFile file, String id) {
//
//
//        byte[] bytes = null;
//        try {
//             bytes = file.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("get bytes file error");
//            return null;
//        }
//
//        String fileName = id + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        String pathFile = propertiesConfig.getPathImg() + File.separator + fileName;
//        Path path = Paths.get(pathFile);
//        try {
//            Files.write(path,bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("save file error");
//            return null;
//        }
//        return BaseResponse.builder().data(pathFile).errorCode(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build();
//    }
//
//    @Override
//    public BaseResponse uploadFileDriver(MultipartFile file, String id) {
//
//        return null;
//    }
//}
