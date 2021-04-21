package edu.hytc.moon.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeWork {

    private int id;

    private String homeWorkTitle;

    private String homeWorkContent;

    private String homeWorkFilePath;

    private String homeWorkStatus;

    private String deleteFlag;

    private int classId;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;

    private String inResultScore;

    private String inType;

    private String subjectId;

    private String subjectName;

    private int saveSubjectId;
}
