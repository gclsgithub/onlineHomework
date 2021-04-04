package edu.hytc.moon.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Command {
    private int commandId;

    private int ownerId;

    private String commandContent;

    private String deleteFlag;

    private LocalDateTime commandCreateTime;

    private int createUserId;

    private LocalDateTime commandUpdatTime;

    private int updateUserId;
}
