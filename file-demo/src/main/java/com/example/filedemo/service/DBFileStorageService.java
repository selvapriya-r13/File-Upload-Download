package com.example.filedemo.service;

import com.example.filedemo.entity.UserData;
import com.example.filedemo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DBFileStorageService {
    @Autowired
    private UserRepository userRepository;
}

