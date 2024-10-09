package com.application.usedallea.img.Service;

import com.application.usedallea.img.dto.ImgRegisterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImgService {
    long saveImg(List<MultipartFile> uploadImg, ImgRegisterDTO productImgDTO) throws IOException;

    List<String> findImgByUUID(long imgId);
}
