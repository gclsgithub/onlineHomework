package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.Student;
import edu.hytc.moon.domain.Subject;
import java.util.List;

import edu.hytc.moon.vo.SubjectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubjectMapper {

    @Select("SELECT id,subject_name,subject_desp,teacher_id FROM subject where delete_flag = '1' AND teacher_id = #{id} ")
    List<Subject> findSubjectsByTeacherId(@Param("id") int id);

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
        " ,teacher_id = #{in.teacherId}"+
        "   where "+
        "         id = #{in.id}")
    int updateSubjectById(@Param("in") Subject subject);

    @Update("UPDATE subject SET " +
        " delete_flag = '0' " +
        " where "+
        " id = #{id}")
    int deleteSubject(@Param("id") Integer id);

    @Select(" SELECT " +
            " t1.student_id " +
            " ,t1.student_name " +
            " FROM student t1 , subject2student t2 " +
            " where " +
            "  t1.student_id = t2.student_id " +
            " AND " +
            " t2.subject_id = #{id} " +
            " AND " +
            " t2.delete_flag = '1'")
    List<Student> findStudnetInfoBySubjectId(@Param("id") Integer subjectId);


    @Update("UPDATE subject2student SET delete_flag = '0' " +
            " WHERE " +
            " subject_id = #{subjectId} " +
            " AND student_id = #{studentId} ")
    int delteReations (@Param("studentId")Integer studentId,@Param("subjectId")Integer subjectId);

    @Insert(" INSERT  INTO subject2student(subject_id,student_id,delete_flag,create_time,create_use,update_time,update_user)" +
            " VALUES(#{subjetcId},#{studentId},'1',NOW(),#{teacherId},NOW(),#{teacherId}) ")
    void addRelation(@Param("subjetcId") int subjetcId, @Param("studentId") int studentId,@Param("teacherId") int teacherId);

    @Select("SELECT count(id) from subject2student where subject_id = #{subjetcId} AND student_id = #{studentId} AND delete_flag = '1'")
    int findStudnetInfoBySubjectIdAndStudentId(int subjetcId, int studentId);
}
