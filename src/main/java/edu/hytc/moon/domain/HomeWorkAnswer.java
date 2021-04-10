package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeWorkAnswer {

    private int id;

    private int homeworkId;

    private int studentId;

    private String answerTitle;

    private String answerContent;

    protected String homeWorkFilePath;

    private String deleteFlag;

    private int  classId;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
