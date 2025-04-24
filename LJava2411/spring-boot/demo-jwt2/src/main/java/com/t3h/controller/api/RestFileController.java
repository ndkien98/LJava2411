//package com.t3h.controller.api;
//
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.File;
//import com.google.api.services.drive.model.FileList;
//import com.t3h.model.response.BaseResponse;
//import com.t3h.service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//@RequestMapping("rest/file")
//public class RestFileController {
//
//    @Autowired
//    private FileService fileService;
//
//    @Autowired
//    private Drive googleDrive;
//
//    @PostMapping("/img/local")
//    public ResponseEntity<?> uploadImgLocal(@RequestBody MultipartFile file,String id){
//        return ResponseEntity.ok(fileService.uploadFileLocal(file,id));
//    }
//
//    @RequestMapping(value = "/get-list", method = RequestMethod.GET)
//    private ResponseEntity<?> getAllGoogleDriveFiles() throws IOException {
//        FileList result = googleDrive.files().list()
//                .setFields("nextPageToken, files(id,webViewLink,name,parents,webContentLink,mimeType)")
//                .execute();
//
//        return ResponseEntity.ok(BaseResponse.builder().data(result).build());
//    }
//
//    @PostMapping(value = "/")
//    private ResponseEntity<?> createNewFolder(@RequestParam(name = "parent", required = false) String parent, @RequestParam(value = "name") String folderName) throws IOException {
//        File fileMetadata = new File();
//        fileMetadata.setName(folderName);
//        fileMetadata.setMimeType("application/vnd.google-apps.folder");
//        if (parent != null) {
//            List<String> parents = Arrays.asList(parent);
//            fileMetadata.setParents(parents);
//        }
//        File file = googleDrive.files().create(fileMetadata).setFields("id,name,webContentLink, webViewLink, parents").execute();
//        return ResponseEntity.ok(BaseResponse.builder().data(file).errorCode(HttpStatus.OK.value()).build());
//    }
//
//}
