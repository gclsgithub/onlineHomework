package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Student;
import edu.hytc.moon.mapper.StudentMapper;
import edu.hytc.moon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {
        return studentMapper.queryAll();
    }

    @Override
    public Student check(Student student) {
        return studentMapper.check(student);
    }

    @Override
    public int queryCOuntALlstu() {
        return studentMapper.queryCOuntALlstu();
    }

    @Override
    public void AddStudent(Student student) {
        studentMapper.AddStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void EditStudent(Student student) {
        studentMapper.EditStudent(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public List<Student> findByCondition(String studentName, String studentAccount) {

        if (!StringUtils.isEmpty(studentName) || !StringUtils.isEmpty(studentAccount)){
            return studentMapper.findAllByCondition(studentName,studentAccount);
        } else {
            return studentMapper.queryAll();
        }

    }
}
