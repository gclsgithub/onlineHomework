package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.HomeWorkAnswer;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.mapper.AnswerResultMapper;
import edu.hytc.moon.mapper.HomeworkanswerMapper;
import edu.hytc.moon.service.HomeworkanswerService;
import edu.hytc.moon.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkanswerServiceImpl implements HomeworkanswerService {

    @Autowired
    HomeworkanswerMapper homeworkanswerMapper;

    @Autowired
    TeacherService teacherService;


    @Autowired
    AnswerResultMapper answerResultMapper;



    @Override
    public List<HomeWorkAnswer> findAnswerByHomeWorkId(Integer homeworkId) {

        return homeworkanswerMapper.findHomeWorkAnswerByHomeWorkId(homeworkId);
    }

    @Override
    public int deleteComment(Integer answerid) {
        return homeworkanswerMapper.deleteAnswerById(answerid);
    }

    @Override
    public List<AnswerResult>  findAnswerResutByAnswerId(Integer answerid) {
        List<AnswerResult> outs = new ArrayList<>();
        List<AnswerResult> finds =  answerResultMapper.finAllByAnswerId(answerid);
        finds.forEach(item->{
            AnswerResult dto = new AnswerResult();
            Teacher teacher =  teacherService.getTeacherById(item.getTeacherId());

            if (teacher!=null){
                BeanUtils.copyProperties(item,dto);
                dto.setTeacherName(teacher.getTeacherName());
            }

            outs.add(dto);
        });
//        return outs;
        return finds;
    }

    @Override
    public List<HomeWorkAnswer> findAnswerByHomeWorkIdAnnStudentId(Integer homeworkId, int studentId) {
        return homeworkanswerMapper.finAllByHomeWorkIdANdStudentId(homeworkId,studentId);
    }

    @Override
    public int saveHomeWorkAnswer(HomeWorkAnswer homeWorkAnswere) {
        return homeworkanswerMapper.saveHomeworkAnswer(homeWorkAnswere);
    }
}
