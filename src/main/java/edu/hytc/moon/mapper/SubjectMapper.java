package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.Subject;
import java.util.List;

import edu.hytc.moon.vo.SubjectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubjectMapper {

    @Select("SELECT id,subject_name,subject_desp,teacher_id FROM subject where delete_flag = '1' AND teacher_id = #{id} ")
    List<Subject> findSubjectsByTeacherId(@Param("id")int id);

    @Select(" SELECT "
        + " t2.id AS id "
        + " ,t2.subject_name AS subjectName "
        + " ,t2.subject_desp AS subjectDesp"
        + " ,t2.teacher_id AS teacherId"
        + " FROM "
            + " subject2student t1, subject t2 "
        + " WHERE "
            + " t1.subject_id = t2.id "
        + " AND "
            + " t1.delete_flag = '1' "
        + " AND "
            + " t2.delete_flag = '1' "
        + " AND "
            + " t1.student_id = #{id} ")
    List<Subject> findSubjectsByStudentId(@Param("stuId") int id);

    @Select(" SELECT  "
        + " id "
        + " ,subject_name "
        + " ,subject_desp "
        + " ,teacher_id "
        + " FROM subject WHERE  delete_flag = '1' AND  id = #{id}")
    Subject findSubjectById(Integer id);

    @Insert("insert into subject(subject_name,subject_desp,teacher_id,delete_flag,create_time,create_use,update_time,update_user) VALUES(" +
            " #{in.subjectName},#{in.subjectDesp},#{in.teacherId},'1',NOW(),#{in.createUse},NOW(),#{in.updateUser})")
    int saveSubject(@Param("in") Subject subject);

    @Update("UPDATE subject SET " +
            " subject_name = #{in.subjectName} " +
            " ,subject_desp = #{in.subjectDesp} " +
            " ,update_time = NOW() " +
            " ,update_user = #{in.updateUser}" +
            " ,teacher_id = #{in.teacherId}")
    int updateSubjectById(Subject subject);
}
