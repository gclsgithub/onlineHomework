package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Subject;
import edu.hytc.moon.mapper.SubjectMapper;
import edu.hytc.moon.service.SubjectService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    SubjectMapper subjectMapper;

    @Override
    public List<Subject> findSubjectByTeacherId(int id, boolean b) {
        if (b){
            //Teacher場合
            return subjectMapper.findSubjectsByTeacherId(id);
        }else {
            //学生
            return subjectMapper.findSubjectsByStudentId(id);
        }
    }

    @Override
    public Subject findSubjectById(Integer id) {
        return subjectMapper.findSubjectById(id);
    }
}
