package edu.hytc.moon.mapper;

import edu.hytc.moon.domain.InfoEntity;
import edu.hytc.moon.domain.Paper;
import edu.hytc.moon.vo.InfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InfoMapper {

    @Select(" SELECT id,title,content FROM  info where  delete_flag = '1'")
    List<InfoEntity> findAllInfos();

    @Select(" SELECT id,title,content FROM  info where  delete_flag = '1' and id = #{id}")
    InfoEntity findAllInfoById(int id );

    @Update(" UPDATE info set delete_flag = '0' ,update_user = #{upUser},update_time=NOW() where id = #{id} ")
    int deteInfoById(int id,int upUser);

    @Update(" UPDATE info set title = #{title} ,content = #{content},update_user = #{updateUser},update_time=NOW()  where id = #{id}")
    int modifyInfoById(InfoEntity infoEntity);

    @Insert( "insert info (title,content,delete_flag,create_time,create_use,update_time,update_user) " +
            " VALUES(#{title},#{content},'1',NOW(),#{createUse},NOW(),#{updateUser})")
    int inserInfo(InfoEntity entity);

    @Select(" SELECT id,title,content FROM  info where  delete_flag = '1' AND (title like concat('%',#{info.title},'%') OR  content like  concat('%',#{info.content},'%') )")
    List<InfoEntity> findInfoByCondition(@Param("info") InfoVo infoVo);
}
