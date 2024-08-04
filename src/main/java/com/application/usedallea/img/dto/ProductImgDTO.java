package com.application.usedallea.img.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProductImgDTO {
	
	private long imgId;
	private long imgSeq;
	private String imgUUID;
	private String originalName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	private Date updatedAt;



}
