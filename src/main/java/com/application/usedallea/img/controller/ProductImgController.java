package com.application.usedallea.img.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/img")
public class ProductImgController {
	
	@Value("${file.repo.path}")
	private String imgRepositoryPath;


	@GetMapping("/setThumbnail")
	@ResponseBody
	public UrlResource setThumbnail(@RequestParam("imgName") String imgName) throws IOException {
		UrlResource resource = new UrlResource("file:"+imgRepositoryPath + imgName);
		return resource;
	}





}
