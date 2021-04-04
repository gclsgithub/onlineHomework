package edu.hytc.moon.domain;

import lombok.*;

@Data
public class Classe {
    private int classeId;
    private String classeName;
    private int teacherId;
    //声明类，用于关联
    private Teacher teacher;
}
