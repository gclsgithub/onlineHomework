package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Readcomment {

    private Integer id;

    private Integer homeworkId;

    private String commentTitle;

    private String commentContent;

    private String commentUserType;

    private String deleteFlag;

    private int classId;

    private int commentUser;

    private String ownCommentUser;

    private String commentUserName;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
