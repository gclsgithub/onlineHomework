package edu.hytc.moon.controller;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
public class FileController {

    @RequestMapping("/filedownload")
    @ResponseBody
    public void download (@RequestParam("path") String path , HttpServletResponse response, HttpServletRequest request)  throws IOException {

        File file = new File(path);
        //判断文件是否存在
        if(!file.exists()) {
            return;
        }
        //判断文件类型
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);

        //设置文件响应大小
        response.setContentLengthLong(file.length());

        //文件名编码，解决乱码问题
        String fileName = file.getName();
        String encodedFileName = null;
        String userAgentString = request.getHeader("User-Agent");
        String browser = UserAgent.parseUserAgentString(userAgentString).getBrowser().getGroup().getName();
        if(browser.equals("Chrome") || browser.equals("Internet Exploer") || browser.equals("Safari")) {
            encodedFileName = URLEncoder.encode(fileName,"utf-8").replaceAll("\\+", "%20");
        } else {
            encodedFileName = MimeUtility.decodeText(fileName);
        }

        //设置Content-Disposition响应头，一方面可以指定下载的文件名，另一方面可以引导浏览器弹出文件下载窗口
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + encodedFileName + "\"");

        //文件下载
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(in, response.getOutputStream());
    }
}
