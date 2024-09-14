package com.application.usedallea.img.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/img")

public class ImgController {

    @Value("${file.repo.path}")
    private String imgRepositoryPath;

    @GetMapping("/setThumbnail")
    @ResponseBody
    public UrlResource setImg(@RequestParam("imgName")String imgName) throws IOException {
        return new UrlResource("file:/"+imgRepositoryPath+imgName);
    }

}
