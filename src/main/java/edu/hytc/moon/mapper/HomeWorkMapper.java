package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.HomeWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomeWorkMapper {

    @Select("select t1.*, t2.teacher_name as createUseName from homework t1,teacher t2 where t1.delete_flag = 1 AND  t1.class_id = #{classId} AND t1.create_use = t2.teacher_id ")
    List<HomeWork> findSomeClassHomeWork(@Param("classId") int classId);

    @Select("SELECT * from homework where delete_flag = 1")
    HomeWork findById(int homeworkId);
}
