package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Subject;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.mapper.SubjectMapper;
import edu.hytc.moon.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

import edu.hytc.moon.vo.StudentFindVo;
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
    public int updateSubject(SubjectVo subjectvo, Teacher teacher) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectvo,subject);

        subject.setId(Integer.parseInt(subjectvo.getId()));

        subject.setTeacherId(teacher.getTeacherId());
        subject.setCreateUse(teacher.getTeacherId());
        subject.setUpdateUser(teacher.getTeacherId());

        return subjectMapper.updateSubjectById(subject);
    }

    @Override
    public int saveSubject(SubjectVo subjectvo,Teacher teacher) {

        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectvo,subject);

        subject.setTeacherId(teacher.getTeacherId());
        subject.setCreateUse(teacher.getTeacherId());
        subject.setUpdateUser(teacher.getTeacherId());

        return subjectMapper.saveSubject(subject);
    }

    @Override
    public int deleteSubject(Integer id) {
        return subjectMapper.deleteSubject(id);
    }

    @Override
    public List<StudentFindVo> findStudnetBySubjectId(Integer subjectId) {
        List<Student> outStudnetlist = subjectMapper.findStudnetInfoBySubjectId(subjectId);
        List<StudentFindVo> out = new ArrayList<>();
        outStudnetlist.forEach(item -> {
            StudentFindVo studentVo = new StudentFindVo();
            studentVo.setStudentId(String.valueOf(item.getStudentId()));
            studentVo.setStudentName(item.getStudentName());

            out.add(studentVo);
        });
        return out;
    }

    @Override
    public void deleteRalations(Integer studentId, String subjectId) {
        subjectMapper.delteReations(studentId,Integer.parseInt(subjectId));
    }

    @Override
    public void addRelation(int subjetcId, int studentId,int teacherId) {

        int count = subjectMapper.findStudnetInfoBySubjectIdAndStudentId(subjetcId,studentId);
        if (count == 0){
            subjectMapper.addRelation(subjetcId,studentId,teacherId);
        }
    }


}
