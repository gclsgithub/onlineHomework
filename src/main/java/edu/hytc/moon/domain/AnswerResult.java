package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerResult {


    private int id;

    private int homeworkId;

    private int classId;

    private int teacherId;

    protected int homeworkAnswerId;


    protected String techerCommment;

    protected String teacherName;

    protected String score;

    private String deleteFlag;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;


}
