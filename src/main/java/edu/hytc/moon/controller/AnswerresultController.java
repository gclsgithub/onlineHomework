package edu.hytc.moon.controller;


import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.Readcomment;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.service.AnswerResultService;
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
@RequestMapping("/answerresult")
public class AnswerresultController {


    @Autowired
    private AnswerResultService answerResultService;

    //跳转到新增
    @RequestMapping("/addAnswerResult")
    public String doAddHomeWork(AnswerResult answerResult, Model model, HttpServletRequest request){
        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        answerResultService.addNewAnswerResult(answerResult);

        return "redirect:/answer/modifyAnswer/"+answerResult.getHomeworkAnswerId();
    }

    //获取所有记录
    @RequestMapping("/deleteRecore/{id}")
    public String deleteRecore(AnswerResult answerResult, @PathVariable("id") Integer answerResultId){

        answerResultService.deleteAnswersult(answerResultId);

        return "redirect:/homework/jump2read/"+answerResult.getHomeworkId();
    }
}
