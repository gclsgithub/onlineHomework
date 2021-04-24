package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.HomeWorkAnswer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomeworkanswerMapper {

    @Select(" SELECT " +
            "   t1.id as id" +
            " , t1.homework_id as homeworkId" +
            " , t1.student_id as studentId" +
            " , t1.answer_title as answerTitle" +
            " , t1.answer_content  as answerContent" +
            " , t1.home_work_filePath as homeWorkFilePath" +
            " , t1.delete_flag as deleteFlag" +
            " , t1.class_id as classId" +
            " , t2.student_name as studentName" +
            " FROM" +
            "      homeworkanswer t1" +
            " LEFT JOIN student t2" +
            " ON" +
            " t1.student_id = t2.student_id " +
            " WHERE" +
            "      delete_flag = 1 " +
            " AND " +
            "      homework_id = #{homeworkId}")
    List<HomeWorkAnswer> findHomeWorkAnswerByHomeWorkId(@Param("homeworkId") Integer homeworkId);

    @Update("UPDATE homeworkanswer" +
            " SET" +
            " delete_flag = 0" +
            " WHERE" +
            " id = #{answerid}")
    int deleteAnswerById(@Param("answerid") Integer answerid);

    @Select(" SELECT " +
            "   id as id" +
            " , homework_id as homeworkId" +
            " , student_id as studentId" +
            " , answer_title as answerTitle" +
            " , answer_content  as answerContent" +
            " , home_work_filePath as homeWorkFilePath" +
            " , delete_flag as deleteFlag" +
            " , class_id as classId" +
            " FROM" +
            "      homeworkanswer"+
            " WHERE" +
            "      delete_flag = 1" +
            " AND " +
            "      homework_id = #{homeworkId}" +
            " AND " +
            "    student_id = #{studentId} ")
    List<HomeWorkAnswer> finAllByHomeWorkIdANdStudentId(Integer homeworkId, int studentId);

    @Insert(" INSERT INTO homeworkanswer (homework_id,student_id,answer_title,answer_content,home_work_filePath,delete_flag,class_id,create_time,create_use,update_time,update_user)" +
            " VALUES (#{i.homeworkId},#{i.studentId},#{i.answerTitle},#{i.answerContent},#{i.homeWorkFilePath},'1',#{i.classId},NOW(),#{i.createUse},NOW(),#{i.updateUser})")
    int saveHomeworkAnswer(@Param("i") HomeWorkAnswer homeWorkAnswere);
}
