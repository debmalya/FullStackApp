package com.rakuten.fullstackrecruitmenttest.controller;


import com.rakuten.fullstackrecruitmenttest.payload.UploadResponse;
import com.rakuten.fullstackrecruitmenttest.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MainController {

    public static final String FILE = "\"file\":";
    private static final Logger logger = Logger.getLogger("MainController");
    @Autowired
    private UploadFileService uploadFileService;


    @PostMapping(value = "/test",produces = "application/json")
    @CrossOrigin
    public ResponseEntity<UploadResponse> testMethodWithString(@RequestBody String request) {

        int index = request.lastIndexOf(FILE);
        String data = request.substring(index + FILE.length() + 1, request.length() - 2);



        data = data.replace("\\r\\n",";");
        String[] rowWiseData = data.split(";");


        ResponseEntity<UploadResponse> uploadResponseResponseEntity = null;
        try {
            uploadResponseResponseEntity = new ResponseEntity<>(uploadFileService.processCSVData(rowWiseData), HttpStatus.OK);
        } catch(Throwable th){
            logger.log(Level.SEVERE,th.getMessage(),th);
            uploadResponseResponseEntity = new ResponseEntity<>(new UploadResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return uploadResponseResponseEntity;
    }
}
