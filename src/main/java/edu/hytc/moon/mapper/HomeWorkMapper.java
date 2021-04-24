package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.Classe;
import edu.hytc.moon.domain.HomeWork;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomeWorkMapper {

    @Select("select " +
            "t1.id" +
            ",t1.home_work_title as homeWorkTitle" +
            ",t1.home_work_content as homeWorkContent" +
            ",t1.home_work_filePath as  homeWorkFilePath" +
            ",t1.home_work_status as homeWorkStatus" +
            ",t1.class_id as classId" +
//            ",create_time as  createTime" +
            ",t1.create_use as  createUse " +
//            ",update_time as  updateTime " +
            ",t1.update_user as updateUser" +
            ", t2.teacher_name as createUseName from homework t1,teacher t2 where t1.delete_flag = 1 AND t1.create_use = t2.teacher_id ")
    List<HomeWork> findSomeClassHomeWork(@Param("classId") int classId);

    @Select("SELECT * from homework where delete_flag = 1 AND id = #{homeworkId}")
    HomeWork findById(int homeworkId);

    @Select("SELECT " +
            "t1.id" +
            ",t1.home_work_title as homeWorkTitle" +
            ",t1.home_work_content as homeWorkContent" +
            ",t1.home_work_filePath as  homeWorkFilePath" +
            ",t1.home_work_status as homeWorkStatus" +
            ",t1.class_id as classId" +
            ",t1.create_use as  createUse " +
            ",t1.update_user as updateUser" +
            ",t3.subject_name subjectName" +
            " from homework t1 " +
            " LEFT JOIN subject2homework t2 ON t1.id = t2.homework_id " +
            " LEFT JOIN subject t3 ON t2.subject_id = t3.id "
        + " where "
        + " t1.update_user = #{teacherId} "
        + " AND "
        + " t1.delete_flag = 1 "
        + " AND"
        + " t2.delete_flag = 1 "
        + " AND"
        + " t3.delete_flag = 1 ")
    List<HomeWork> findByOwnerId(@Param("teacherId") int teacherId);

    @Insert("insert into homework (home_work_title,home_work_content,home_work_filePath,home_work_status,delete_flag,class_id,create_time,create_use,update_time,update_user) " +
                         "values  (#{info.homeWorkTitle},#{info.homeWorkContent},'','0','1',#{info.classId},NOW(),#{info.createUse},NOW(),#{info.updateUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveHomeWork(@Param("info") HomeWork homeWork);

    @Update("update homework set delete_flag = 0 where id = #{homeworkId}")
    int deleteHomeWorkById(@Param("homeworkId") Integer homeworkId);

    @Select("SELECT count(id) FROM  homework where delete_flag = 1")
    int qurrayALl();

    @Select("select " +
            "t1.id" +
            ",t1.home_work_title as homeWorkTitle" +
            ",t1.home_work_content as homeWorkContent" +
            ",t1.home_work_filePath as  homeWorkFilePath" +
            ",t1.home_work_status as homeWorkStatus" +
            ",t1.class_id as classId" +
//            ",create_time as  createTime" +
            ",t1.create_use as  createUse " +
//            ",update_time as  updateTime " +
            ",t1.update_user as updateUser" +
            ", t2.teacher_name as createUseName " +
            "from " +
            " homework t1 " +
            " ,teacher t2 " +
            " ,subject2homework t3" +
            " , ( SELECT subject_id FROM  subject2student where student_id = #{studentId} AND delete_flag = '1') t4" +
            " where t1.delete_flag = 1 AND t1.create_use = t2.teacher_id " +
            " AND " +
            "   t3.homework_id = t1.id " +
            " AND " +
            "   t3.subject_id = t4.subject_id " +
            " AND " +
            "  t3.delete_flag  = '1' ")
    List<HomeWork> findHomeWorkByStudentId(Integer studentId, Classe classe);
}
