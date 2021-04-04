package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.HomeWork;
import edu.hytc.moon.domain.Student;
import edu.hytc.moon.mapper.ExamMapper;
import edu.hytc.moon.mapper.HomeWorkMapper;
import edu.hytc.moon.service.HomeWorkService;
import edu.hytc.moon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    private HomeWorkMapper homeWorkMapper;

    @Autowired
    private StudentService studentService;


    @Override
    public List<HomeWork> getAll(int seeId) {

        Student student = studentService.getStudentById(seeId);
        List<HomeWork>  homeWorkList = homeWorkMapper.findSomeClassHomeWork(student.getClasseId());

        return homeWorkList;
    }

    @Override
    public HomeWork findHomeworkId(int homeworkId) {

        HomeWork homeWork = homeWorkMapper.findById(homeworkId);
        return homeWork;
    }
}
