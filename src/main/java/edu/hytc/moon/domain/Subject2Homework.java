package edu.hytc.moon.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Subject2Homework {
    private int id;

    private int subjectId;

    private int homeworkId;

    private String deleteFlag;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
