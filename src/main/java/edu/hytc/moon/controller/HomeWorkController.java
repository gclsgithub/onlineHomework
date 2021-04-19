package edu.hytc.moon.controller;

import edu.hytc.moon.domain.*;
import edu.hytc.moon.service.CommentService;
import edu.hytc.moon.service.HomeWorkService;
import edu.hytc.moon.service.HomeworkanswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/homework")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    @Autowired
    private HomeworkanswerService homeworkanswerService;

    @Autowired
    private CommentService commentService;

    //获取所有记录
    @RequestMapping("/getAllHomeWork")
    public String getAllRecord(Model model, HttpServletRequest request){

        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        List<HomeWork> homeWorks=homeWorkService.findByOwnerId(teacher.getTeacherId());
        model.addAttribute("homeWorks",homeWorks);
        return "homework/HomeWorkList";
    }


    //跳转到阅览
    @RequestMapping("/jump2read/{id}")
    public String jump2read(Model model, @PathVariable("id") Integer homeworkId, HttpServletRequest request){

        //查找所有的学生提交
       List<HomeWorkAnswer>  homeWorkAnswers = homeworkanswerService.findAnswerByHomeWorkId(homeworkId);

        model.addAttribute("homeWorkAnswers",homeWorkAnswers);
        model.addAttribute("homeworkId",homeworkId);
        return "homework/ReadHomeWork";
    }



    //跳转到新增
    @RequestMapping("/addHomeWork")
    public String addHomeWork(Model model, HttpServletRequest request){
        return "homework/AddHomeWork";
    }

    //跳转到新增
    @RequestMapping("/addHomeWorkAnswer")
    public String addHomeWorkAnswer(HomeWorkAnswer homeWorkAnswere,Model model, HttpServletRequest request,@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
           //获取原始文件名称
            String originalFilename = file.getOriginalFilename();

            //获取文件后缀名
            String extension = "." + originalFilename.split("\\.")[originalFilename.split("\\.").length-1]; //.jpg
            //获取新文件名称 命名：时间戳+UUID+后缀
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                    + UUID.randomUUID().toString().substring(0, 4)
                    + extension;

            //获取资源路径 classpath的项目路径+/static/files  classpath就是resources的资源路径
            String path = null;
            try {
                path = ResourceUtils.getURL("classpath:").getPath() + "static/files/";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //新建一个时间文件夹标识，用来分类
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //全路径(存放资源的路径) 资源路径+时间文件夹标识
            String dataDir = path + format;
            System.out.println(dataDir);

            //全路径存放在文件类中，判断文件夹是否存在不存在就创建
            File dataFile = new File(dataDir);  //也可以直接放进去进行拼接 File dataFile = new File(path,format);
            if (!dataFile.exists()) {
                dataFile.mkdirs();
            }

            //文件上传至指定路径
            try {
                file.transferTo(new File(dataFile, newFileName));

                homeWorkAnswere.setHomeWorkFilePath(dataFile.getAbsolutePath()+"/"+newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpSession session=request.getSession();

        Student student = (Student)session.getAttribute("loger");

        homeWorkAnswere.setStudentId(student.getStudentId());
        homeWorkAnswere.setClassId(student.getClasseId());
        homeWorkAnswere.setCreateUse(student.getStudentId());
        homeWorkAnswere.setUpdateUser(student.getStudentId());

        homeworkanswerService.saveHomeWorkAnswer(homeWorkAnswere);
        return "redirect:/exam/dohomeWork/"+homeWorkAnswere.getHomeworkId();
    }

    //跳转到新增
    @RequestMapping("/doAddHomeWork")
    public String doAddHomeWork(HomeWork homeWork,HttpServletRequest request){
        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");
        homeWork.setUpdateUser(teacher.getTeacherId());
        homeWork.setCreateUse(teacher.getTeacherId());

        homeWorkService.saveHomeWorkInfo(homeWork);

        return "redirect:/homework/getAllHomeWork";
    }


    //阅览
    @RequestMapping("/doAddHomeWork/{id}")
    public String readHomework(HomeWork homeWork){
        return "";
    }

    //获取所有记录
    @RequestMapping("/showAllComment/{id}")
    public String comment(Model model,  @PathVariable("id") Integer homeworkId,HttpServletRequest request){

        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        List<Readcomment> readcomments = commentService.findByHomeWorkId(homeworkId);


        model.addAttribute("readcomments",readcomments);
        model.addAttribute("ownCommentUser","T"+teacher.getTeacherId());
        model.addAttribute("homeworkId",homeworkId);
        return "homework/HomeComment";
    }

    //获取所有记录
    @RequestMapping("/deleteRecore/{id}")
    public String deleteRecore(@PathVariable("id") Integer homeworkId){
        homeWorkService.deleteHomeWork(homeworkId);
        return "redirect:/homework/getAllHomeWork";
    }



}
