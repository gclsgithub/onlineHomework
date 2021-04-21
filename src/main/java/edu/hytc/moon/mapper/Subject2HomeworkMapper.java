package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.HomeWork;
import edu.hytc.moon.domain.Subject2Homework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Subject2HomeworkMapper {

    @Insert("INSERT INTO subject2homework(subject_id,homework_id,delete_flag,create_time,create_use,update_time,update_user) "
        + "VALUES(#{in.subjectId},#{in.homeworkId},'1',NOW(),#{in.createUse},NOW(),#{in.updateUser})")
    int saveSubject2Homework(@Param("in") Subject2Homework subject2Homework);

}
