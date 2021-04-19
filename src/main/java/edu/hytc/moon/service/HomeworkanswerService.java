package edu.hytc.moon.service;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.HomeWorkAnswer;

import java.util.List;

public interface HomeworkanswerService {
    List<HomeWorkAnswer> findAnswerByHomeWorkId(Integer homeworkId);

    int deleteComment(Integer answerid);

    List<AnswerResult> findAnswerResutByAnswerId(Integer answerid);

    List<HomeWorkAnswer> findAnswerByHomeWorkIdAnnStudentId(Integer homeworkId, int studentId);

    int saveHomeWorkAnswer(HomeWorkAnswer homeWorkAnswere);
}
