package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeWorkResult {

    private int id;

    private  int homeWorkId;

    private  int studentId;

    private String score;

    private String deleteFlag;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
