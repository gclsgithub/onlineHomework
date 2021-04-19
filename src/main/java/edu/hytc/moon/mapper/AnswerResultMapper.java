package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.HomeWorkAnswer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnswerResultMapper {

    @Select(" SELECT " +
            "   t1.id as id" +
            "   ,t1.teacher_id  as teacherId" +
            "   ,t1.techer_commment  as techerCommment" +
            "   ,t1.score  as score" +
            " FROM " +
            " answerResult t1 " +
            " WHERE " +
            " t1.delete_flag = 1" +
            " AND " +
            " t1.homework_answer_id = #{answerid}")
    List<AnswerResult> finAllByAnswerId(@Param("answerid") Integer answerid);

    @Update(" Update " +
            " answerResult " +
            " SET delete_flag = 0" +
            " WHERE id = #{answerResultId}")
    int deleteAnswerResultById(Integer answerResultId);

    @Insert("INSERT INTO answerResult (homework_id,homework_answer_id,teacher_id,techer_commment,score,delete_flag,class_id,create_time,create_use,update_time,update_user)" +
            "VALUES(#{i.homeworkId},#{i.homeworkAnswerId},#{i.teacherId},#{i.techerCommment},#{i.score},'1',#{i.classId},NOW(),#{i.createUse},NOW(),#{i.updateUser})" )
    int inserNewAnswerResult(@Param("i") AnswerResult answerResult);

}
