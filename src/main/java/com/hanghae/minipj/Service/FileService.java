package com.hanghae.minipj.Service;


import com.hanghae.minipj.domain.File;
import com.hanghae.minipj.response.ResponseDto;
import com.hanghae.minipj.File;
import com.hanghae.minipj.S3.S3Uploader;
import com.hanghae.minipj.domain.Member;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.jwt.TokenProvider;
import com.hanghae.minipj.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final TokenProvider tokenProvider;

    private final S3Uploader s3Uploader;

    private final FileRepository fileRepository;


    public ResponseDto<Object> upload(MultipartFile multifile, HttpServletRequest request) throws IOException {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        String imageUrl = s3Uploader.uploadFiles(multifile,"static");

        File file = File.builder()
                .url(imageUrl)
                .build();
        fileRepository.save(file);

        return ResponseDto.success(imageUrl);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
}

