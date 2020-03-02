package com.mouse.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/*
 *created by mouse on 2020/2/29
 */
@Controller
public class UploadController {

    @GetMapping("/file")
    public String toFile(){
        return "file";
    }

    @PostMapping("/upload")
    public String Upload(@RequestParam(value = "file")MultipartFile file, RedirectAttributes attributes){
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        //  上传的文件名称
        String fileName = file.getOriginalFilename();
        //  上传文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //  上传至文件夹路径
        String filePath = "src/main/resources/static/images/";
        //  生成新的文件名
        fileName = UUID.randomUUID()+suffixName;

        //  保存方法一
        /*
        File uploadFile = new File(filePath + fileName);
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //  保存方法二
        try {
            byte[] bytes = file.getBytes();
            Path p = Paths.get(filePath+fileName);
            Files.write(p, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //  回显本地资源地址
        String url = "/file/" + fileName;
        attributes.addFlashAttribute("url", url);
        //  想法尝试直接static引用，不行
        attributes.addFlashAttribute("path", "/images/"+fileName);
        return "redirect:/file";
    }
}
