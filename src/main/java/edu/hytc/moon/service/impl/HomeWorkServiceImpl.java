package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.HomeWork;
import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Subject2Homework;
import edu.hytc.moon.mapper.ExamMapper;
import edu.hytc.moon.mapper.HomeWorkMapper;
import edu.hytc.moon.mapper.Subject2HomeworkMapper;
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
    private Subject2HomeworkMapper subject2HomeworkMapper;

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

    @Override
    public List<HomeWork> findByOwnerId(int teacherId) {

        List<HomeWork> homeWorks = homeWorkMapper.findByOwnerId(teacherId);

        return homeWorks;
    }

    @Override
    public int saveHomeWorkInfo(HomeWork homeWork) {
        int count = homeWorkMapper.saveHomeWork(homeWork);


        int homeworkId = homeWork.getId();
        int subjectId = homeWork.getSaveSubjectId();

        Subject2Homework subject2Homework = new Subject2Homework();

        subject2Homework.setHomeworkId(homeworkId);
        subject2Homework.setSubjectId(subjectId);
        subject2Homework.setCreateUse(homeWork.getCreateUse());
        subject2Homework.setUpdateUser(homeWork.getUpdateUser());

        subject2HomeworkMapper.saveSubject2Homework(subject2Homework);
        return count;
    }

    @Override
    public void deleteHomeWork(Integer homeworkId) {
        homeWorkMapper.deleteHomeWorkById(homeworkId);
    }

    @Override
    public int queryCountAllPas() {
        return homeWorkMapper.qurrayALl();
    }

    @Override
    public List<HomeWork> getHomeWorkByStudentId(Integer studentId) {
        Student student = studentService.getStudentById(studentId);
        List<HomeWork>  homeWorkList = homeWorkMapper.findHomeWorkByStudentId(studentId,student.getClasse());

        return homeWorkList;
    }
}
