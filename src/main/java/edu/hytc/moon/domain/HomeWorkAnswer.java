package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeWorkAnswer {

    private int id;

    private int homeworkId;

    private int studentId;

    private String answerTitile;

    private String answerContent;

    protected String answerFilePath;

    private String deleteFlag;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
