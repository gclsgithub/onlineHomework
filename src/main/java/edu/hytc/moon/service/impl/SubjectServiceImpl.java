package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Subject;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.mapper.SubjectMapper;
import edu.hytc.moon.service.SubjectService;
import java.util.List;

import edu.hytc.moon.vo.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
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

    @Override
    public void updateSubject(SubjectVo subjectvo, Teacher teacher) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectvo,subject);

        subject.setId(Integer.parseInt(subjectvo.getSubjectDesp()));

        subject.setTeacherId(teacher.getTeacherId());
        subject.setCreateUse(teacher.getTeacherId());
        subject.setUpdateUser(teacher.getTeacherId());

        subjectMapper.updateSubjectById(subject);
    }

    @Override
    public void saveSubject(SubjectVo subjectvo,Teacher teacher) {

        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectvo,subject);

        subject.setTeacherId(teacher.getTeacherId());
        subject.setCreateUse(teacher.getTeacherId());
        subject.setUpdateUser(teacher.getTeacherId());

        subjectMapper.saveSubject(subject);
    }


}
