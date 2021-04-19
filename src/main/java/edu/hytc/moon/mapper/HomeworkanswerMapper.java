package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.HomeWorkAnswer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomeworkanswerMapper {

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
