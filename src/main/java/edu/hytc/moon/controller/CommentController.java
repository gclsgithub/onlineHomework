package edu.hytc.moon.controller;

import edu.hytc.moon.domain.HomeWork;
import edu.hytc.moon.domain.Readcomment;
import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //跳转到新增
    @RequestMapping("/addComment")
    public String doAddHomeWork(Readcomment readcomment, HttpServletRequest request){
        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");


        readcomment.setCommentUser(teacher.getTeacherId());
        readcomment.setCreateUse(teacher.getTeacherId());
        readcomment.setUpdateUser(teacher.getTeacherId());
        readcomment.setClassId(-1);

        commentService.saveReadComment(readcomment);
        return "redirect:/homework/showAllComment/"+readcomment.getHomeworkId();
    }

    //跳转到新增
    @RequestMapping("/addStuComment")
    public String addStuComment(Readcomment readcomment, HttpServletRequest request){
        HttpSession session=request.getSession();

        Student student = (Student)session.getAttribute("loger");


        readcomment.setCommentUser(student.getStudentId());
        readcomment.setCreateUse(student.getStudentId());
        readcomment.setUpdateUser(student.getStudentId());
        readcomment.setClassId(student.getClasseId());

        commentService.saveReadComment(readcomment);
        return "redirect:/exam/dohomeWork/"+readcomment.getHomeworkId();
    }

    //获取所有记录
    @RequestMapping("/deleteRecore/{id}")
    public String deleteRecore(Readcomment readcomment,@PathVariable("id") Integer commentId){

        commentService.deleteComment(commentId);

        return "redirect:/homework/showAllComment/"+readcomment.getHomeworkId();
    }

    //获取所有记录
    @RequestMapping("/deleteRecorest/{id}")
    public String deleteRecorest(Readcomment readcomment,@PathVariable("id") Integer commentId){

        commentService.deleteComment(commentId);

        return "redirect:/exam/toHomeWork/"+readcomment.getHomeworkId();
    }

}
