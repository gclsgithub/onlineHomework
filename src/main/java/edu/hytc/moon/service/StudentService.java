package edu.hytc.moon.service;

import edu.hytc.moon.domain.Student;

import java.util.List;

public interface StudentService {
    //查看所有用户
    List<Student> getAll();
    //验证用户是否存在
    Student check(Student student);
    //统计所有学生
    int queryCOuntALlstu();
    //添加学生
    void AddStudent(Student student);
    //根据id查询学生信息
    Student getStudentById(Integer id);
    //修改学生信息
    void EditStudent(Student student);
    //删除学生通过id
    void deleteById(Integer id);

    List<Student> findByCondition(String studentName, String studentAccount);
}
