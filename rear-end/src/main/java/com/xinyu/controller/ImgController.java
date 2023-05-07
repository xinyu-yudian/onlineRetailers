package com.xinyu.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
public class ImgController {

    @RequestMapping(value = "/getImg/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@PathVariable("name") String name,HttpServletRequest req) throws IOException {
        String readPath = req.getServletContext().getRealPath("/images")+"/adver/"+name;
        System.out.println(readPath);
        File file = new File(readPath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}
