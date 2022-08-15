package com.hanghae.minipj.Controller;

import com.hanghae.minipj.Service.FileService;
import com.hanghae.minipj.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/api/auth/images")
    public ResponseDto<?> uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws IOException {
        return fileService.upload(file, request);
    }
}
