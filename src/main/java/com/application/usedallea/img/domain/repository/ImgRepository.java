package com.application.usedallea.img.domain.repository;



import com.application.usedallea.img.domain.entity.Img;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgRepository {

    Img findMaxImgId();

    void save(Img productImg);

    String findImgUUIDById(long imgId);
}
