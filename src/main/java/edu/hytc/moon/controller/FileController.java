package edu.hytc.moon.controller;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileController {

    @RequestMapping("/filedownload")
    public void download (@RequestParam("path") String path , HttpServletResponse response) throws FileNotFoundException {

        System.out.println(path);

        FileInputStream fis = new FileInputStream(new File(path));

        //获取输出流
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //利用IO流工具类实现流文件的拷贝，（输出显示在浏览器上在线打开方式）
        try {
            IOUtils.copy(fis, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);
    }
}
