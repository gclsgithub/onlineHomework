package edu.hytc.moon.controller;

import edu.hytc.moon.domain.Subject;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.service.SubjectService;
import edu.hytc.moon.vo.SubjectVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @RequestMapping("/findAllSubject")
    public String getAllRecord(Model model, HttpServletRequest request){
        HttpSession session=request.getSession();

            //maanag
//        Student student = (Student)session.getAttribute("loger");
        Teacher teacher = (Teacher)session.getAttribute("logerd");
        List<Subject> subjects = subjectService.findSubjectByTeacherId(teacher.getTeacherId(),true);
        model.addAttribute("subjects",subjects);
        return  "subject/subjectList";
    }

    @RequestMapping("/editSubject/{id}")
    public String editSubject(@PathVariable("id") Integer id,Model model){

        model.addAttribute("id",id);

        Subject subject = subjectService.findSubjectById(id);

        model.addAttribute("subjectName",subject.getSubjectName());
        model.addAttribute("subjectDesp",subject.getSubjectDesp());

        return "subject/editSubject";
    }

    @RequestMapping("/doeditSubject")
    public String doEditSubject(SubjectVo subject,HttpServletRequest request){
        HttpSession session=request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("logerd");
        if (!ObjectUtils.isEmpty(subject.getId())){
            subjectService.updateSubject(subject,teacher);
        } else{
            subjectService.saveSubject(subject,teacher);
        }
        return "redirect:/subject/findAllSubject";
    }

    @RequestMapping("/addSubject")
    public String addSubject(){
        return "subject/editSubject";
    }


    @RequestMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable("id") Integer id){

        subjectService.deleteSubject(id);
        return "redirect:/subject/findAllSubject";
    }

}
