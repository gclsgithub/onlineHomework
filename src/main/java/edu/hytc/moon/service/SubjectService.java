package edu.hytc.moon.service;

import edu.hytc.moon.domain.Subject;
import java.util.List;

public interface SubjectService {

    /**
     *
     * @param id 查询用户id
     * @param b 是否是教师
     */
    List<Subject> findSubjectByTeacherId(int id, boolean b);

    Subject findSubjectById(Integer id);
}
