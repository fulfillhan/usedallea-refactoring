package com.application.usedallea.img.Service;

import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.domain.repository.ImgRepository;
import com.application.usedallea.img.dto.ImgRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImgServiceImpl implements ImgService {

    @Value("${file.repo.path}")
    private String imgRepositoryPath;

    private final ImgRepository imgRepository;

    @Override
    public long saveImg(List<MultipartFile> uploadImg, ImgRegisterDto imgDto) throws IOException {
        if (uploadImg.isEmpty()) {
            throw new RuntimeException("file is empty");
        }
        long imgSeq = 1;
        for (MultipartFile imgFile : uploadImg) {
            saveSingleImgFile(imgFile, imgSeq, imgDto);
            imgSeq++;
        }
        Img lastSavedImg = imgRepository.findTopByOrderByID();
        if (lastSavedImg == null) {
            throw new RuntimeException("NO Img saved");
        }
        return lastSavedImg.getImgId();
    }

    @Override
    public List<String> findImgByUUID(long imgId) {
        List<String> imgUUIDlList = new ArrayList<>();
        Img img = imgRepository.findById(imgId);
        String imgUUID = img.getImgUUID();
        imgUUIDlList.add(imgUUID);
        return imgUUIDlList;
    }

    public void saveSingleImgFile(MultipartFile imgFile, long imgSeq, ImgRegisterDto imgDto) throws IOException {
        String originalImgName = imgFile.getOriginalFilename();
        if (originalImgName == null) {
            throw new RuntimeException("originalImgName is null");
        }
        String imgUUID = createUUID(originalImgName);
        Img img = Img.buildImg(imgDto.getImgId(), imgSeq, originalImgName, imgUUID);
        imgFile.transferTo(new File(imgRepositoryPath + imgUUID));
        imgRepository.save(img);
    }

    public String createUUID(String originalImgName) {
        UUID uuid = UUID.randomUUID();
        String extension = originalImgName.substring(originalImgName.lastIndexOf("."));
        return uuid + extension;
    }


}
