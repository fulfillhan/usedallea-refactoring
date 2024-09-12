package com.application.usedallea.img.domain.repository;



import com.application.usedallea.img.domain.entity.Img;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgRepository {

    void save(Img productImg);

    Img findById(long imgId);

    Img findTopByOrderByID();
}
