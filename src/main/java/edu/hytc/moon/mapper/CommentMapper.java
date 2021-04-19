package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.Readcomment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {


    @Select("SELECT  " +
            "id as id" +
            ",homework_id as homeworkId" +
            ",comment_title as commentTitle" +
            ",comment_content as commentContent" +
            ",comment_user as commentUser" +
            ",class_id as classId" +
            " FROM  readcomment WHERE homework_id = #{homeworkId} and delete_flag = 1")
    List<Readcomment> findByHomeWorkId(@Param("homeworkId") Integer homeworkId);

    @Update("UPDATE readcomment set delete_flag = 0 where  id = #{commentId}")
    int deleteCommentById(@Param("commentId") Integer commentId);

    @Insert("INSERT INTO readcomment(homework_id,comment_title,comment_content,comment_user_type,comment_user,delete_flag,class_id,create_time,create_use,update_time,update_user)" +
            "VALUES(#{i.homeworkId}, #{i.commentTitle},#{i.commentContent},'',#{i.commentUser},'1',#{i.classId},NOW(),#{i.createUse},NOW(),#{i.updateUser})")
    int saveComment(@Param("i") Readcomment readcomment);
}
