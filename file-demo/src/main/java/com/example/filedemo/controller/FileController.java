package com.example.filedemo.controller;

import com.example.filedemo.entity.UserData;
import com.example.filedemo.repository.UserRepository;
import com.example.filedemo.service.DBFileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            //check the uploaded file is json
            if (!file.getContentType().equals("application/json")) {
                return ResponseEntity.badRequest().body("Only json files are allowed");
            }
            //Read and parse the json data
            ObjectMapper objectMapper = new ObjectMapper();
            UserData userData = objectMapper.readValue(file.getInputStream(), UserData.class);
            //save entity to database
            userRepository.save(userData);
            return ResponseEntity.ok("Data uploaded and saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occured while processing the file:" + e.getMessage());
        }
    }
    @Autowired
        private DBFileStorageService dbFileStorageService;
    @GetMapping("/download")
    public void generatePdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("ContentDisposition", "attachment; filename= userData.pdf");
            //Create a document
            Document document = new Document();
            //create pdf writer to write the response
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            //add content to pdf
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    private void addTableHeader(PdfPTable table) {
        table.addCell("Id");
        table.addCell("Name");
        table.addCell("Age");
    }
    private void addRows(PdfPTable table) {
        //Fetch data from DB
        List<UserData> userData = getUserData();
        for (UserData userData1 : userData) {
            table.addCell(String.valueOf(userData1.getId()));
            table.addCell(userData1.getName());
            table.addCell(String.valueOf(userData1.getAge()));
        }
    }
   private List<UserData> getUserData() {
        return userRepository.findAll();
    }
}










