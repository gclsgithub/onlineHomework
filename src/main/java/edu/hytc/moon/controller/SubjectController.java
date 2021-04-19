package edu.hytc.moon.controller;

import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/subject")
public class SubjectController {




    @RequestMapping("/findAllSubject")
    public String getAllRecord(Model model, HttpServletRequest request){
        HttpSession session=request.getSession();

        List<>

        Student student = (Student)session.getAttribute("loger");

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        if (teacher != null ){

        } else if (student != null){

        }

        model.addAttribute("homeWorks",homeWorks);

        return  "";


    }
}
