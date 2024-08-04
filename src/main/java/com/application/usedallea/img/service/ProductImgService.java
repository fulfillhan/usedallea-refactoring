package com.application.usedallea.img.service;


import com.application.usedallea.img.dto.ProductImgDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductImgService {
	public long saveImg(List<MultipartFile> uploadImg, ProductImgDTO productImgDTO) throws IOException;
}
