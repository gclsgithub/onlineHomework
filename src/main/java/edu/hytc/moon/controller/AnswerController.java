package edu.hytc.moon.controller;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.Readcomment;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.service.HomeworkanswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/answer")
public class AnswerController {


    @Autowired
    private HomeworkanswerService homeworkanswerService;


    //跳转到新增
    @RequestMapping("/modifyAnswer/{id}")
    public String doAddHomeWork(@PathVariable("id") Integer answerid, Model model, HttpServletRequest request){
        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        List<AnswerResult> answerResults = homeworkanswerService.findAnswerResutByAnswerId(answerid);

        model.addAttribute("answerResults",answerResults);
        model.addAttribute("answerid",answerid);
        if (answerResults.size() > 0 ){
            model.addAttribute("homeworkId",answerResults.get(0).getHomeworkId());
        } else {
            model.addAttribute("homeworkId","0");
        }


        return "/homework/TreacherResult";
    }

    //获取所有记录
    @RequestMapping("/deleteRecore/{id}")
    public String deleteRecore(Readcomment readcomment,@PathVariable("id") Integer answerid){

        homeworkanswerService.deleteComment(answerid);

        return "redirect:/homework/jump2read/"+readcomment.getHomeworkId();
    }

    //获取所有记录
    @RequestMapping("/deleteRecorest/{id}")
    public String deleteRecorest(Readcomment readcomment,@PathVariable("id") Integer answerid){

        homeworkanswerService.deleteComment(answerid);

        return "redirect:/exam/toHomeWork/"+readcomment.getHomeworkId();
    }

}
