package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Readcomment;
import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.mapper.CommentMapper;
import edu.hytc.moon.mapper.HomeWorkMapper;
import edu.hytc.moon.service.CommentService;
import edu.hytc.moon.service.StudentService;
import edu.hytc.moon.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    @Override
    public List<Readcomment> findByHomeWorkId(Integer homeworkId) {

        List<Readcomment> outList = new ArrayList<>();
        List<Readcomment> readcomments = commentMapper.findByHomeWorkId(homeworkId);

        readcomments.forEach(readcomment -> {

            Readcomment outDeto = new Readcomment();
            BeanUtils.copyProperties(readcomment,outDeto);
            Student student = studentService.getStudentById(readcomment.getCommentUser());

            if (student == null ){
                Teacher teacher = teacherService.getTeacherById(readcomment.getCommentUser());

                if (teacher == null ){

                } else {
                    outDeto.setCommentUserName(teacher.getTeacherName());
                    outDeto.setCommentUserType("老师");
                    outDeto.setOwnCommentUser("T"+readcomment.getCommentUser());
                }
            } else {
                outDeto.setCommentUserName(student.getStudentName());
                outDeto.setCommentUserType("学生");
                outDeto.setOwnCommentUser("S"+readcomment.getCommentUser());
            }
            outList.add(outDeto);
        });
        return outList;
    }

    @Override
    public int deleteComment(Integer commentId) {
        return commentMapper.deleteCommentById(commentId);
    }

    @Override
    public int saveReadComment(Readcomment readcomment) {
        return commentMapper.saveComment(readcomment);
    }


}
