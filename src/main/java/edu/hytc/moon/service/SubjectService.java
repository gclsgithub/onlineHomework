package edu.hytc.moon.service;

import edu.hytc.moon.domain.Subject;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.vo.SubjectVo;

import java.util.List;

public interface SubjectService {

    /**
     *
     * @param id 查询用户id
     * @param b 是否是教师
     */
    List<Subject> findSubjectByTeacherId(int id, boolean b);

    Subject findSubjectById(Integer id);

    void updateSubject(SubjectVo subject, Teacher teacher);

    void saveSubject(SubjectVo subject,Teacher teacher);
}
