package edu.hytc.moon.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Subject {

    private int id;

    private String subjectName;

    private String subjectDesp;

    private int teacherId;

    private String deleteFlag;

    private LocalDateTime createTime;

    private int createUse;

    private String createUseName;

    private LocalDateTime updateTime;

    private int updateUser;

    private String updateUserName;
}
