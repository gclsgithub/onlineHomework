package edu.hytc.moon.controller;

import edu.hytc.moon.domain.Classe;
import edu.hytc.moon.domain.Student;
import edu.hytc.moon.mapper.ClasseMapper;
import edu.hytc.moon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ClasseMapper classeMapper;
    @Autowired
    private StudentService studentService;

    //查看所有学生
    @RequestMapping("/getAllStudent")
    public String getAllStudent(Model model){
        List<Student> students = studentService.getAll();
        model.addAttribute("students",students);
        return "student/studentList";
    }

    //按照条件检索
    @RequestMapping("/findStudetByCondition")
    public String getStudentByCondition(Model model,Student student){
        List<Student> students = studentService.findByCondition(student.getStudentName(),student.getStudentAccount());
        model.addAttribute("students",students);
        model.addAttribute("studentName",student.getStudentName());
        model.addAttribute("studentAccount",student.getStudentAccount());
        return "student/studentList";
    }

//修改编辑功能,先获取该id得学生信息
    @RequestMapping("/{id}")
    public String updateStudent(@PathVariable("id") Integer id,Model model){
        Student student=studentService.getStudentById(id);
        List<Classe> classes = classeMapper.queryAll();
        model.addAttribute("classes",classes);
        model.addAttribute("student",student);
        return "student/studentEdit";
    }
//更改学生信息
    @RequestMapping("/editStudent")
    public String EditStudent(Student student){
        studentService.EditStudent(student);
        return "redirect:/student/getAllStudent";
    }
//删除学生
    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        studentService.deleteById(id);
        return "redirect:/student/getAllStudent";
    }
}
