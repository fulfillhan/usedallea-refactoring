package com.application.usedallea.img.service;

import com.application.usedallea.img.dao.ProductImgDAO;
import com.application.usedallea.img.dto.ProductImgDTO;
import com.application.usedallea.product.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImgServiceImpl implements ProductImgService {
	
	@Value("${file.repo.path}")
	private String imgRepositoryPath;
	
	@Autowired
	private ProductImgDAO productImgDAO;

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public long saveImg(List<MultipartFile> uploadImg, ProductImgDTO productImgDTO) throws IOException {

		// imgDTO라고 해서 for 문 사용
		if(uploadImg.isEmpty()) { // 이미지가 없으면 예외를 발생.
			throw new RuntimeException("file is required"); // 서버가 실행중에 발생중인 예외
		}

		long imgId = productImgDAO.getMaxImgId(); // 이미지 아이디의 최대값을 가지고 온다.   1
		long imgSeq = 1; // imgSeq 초기값을 설정한다.
		for (MultipartFile img : uploadImg) {
			String originalImgName = img.getOriginalFilename();
			productImgDTO.setOriginalName(originalImgName);

			UUID uuid = UUID.randomUUID();
			String extension = originalImgName.substring(originalImgName.lastIndexOf("."));
			String imgUUID = uuid + extension;

			productImgDTO.setImgUUID(imgUUID);
			productImgDTO.setImgId(imgId);
			productImgDTO.setImgSeq(imgSeq);

			img.transferTo(new File(imgRepositoryPath + imgUUID)); //이미지 저장

			productImgDAO.saveImg(productImgDTO);

			imgSeq++;

		}
		return imgId;
		
	}

	
	
}
