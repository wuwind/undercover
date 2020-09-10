package com.wuwind.controller.api;

import com.wuwind.dao.bean.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api")
public class ArticleContoller {

    @RequestMapping("article_edit")
    @ResponseBody
    public List<Game> addArticle(String title, String item_text, String item_img, MultipartFile file) throws IOException {
        System.out.println(title);

        //定义文件保存的本地路径
        String localPath = "D:\\File\\";
        //定义 文件名
        String filename = null;
        if (null != file && !file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到 文件名
            filename = uuid + "." + suffixName;
            //文件保存路径
            String filePath = localPath + filename;
            file.transferTo(new File(filePath));
            System.out.println("filePath:" + filePath);
        }


        return null;
    }
}
