package edu.hytc.moon.domain;

import java.util.List;
import lombok.Data;

@Data
public class PapIdQuesCourse {
    private Integer papid;
    private List<String> questionCourse;

    public PapIdQuesCourse() {
    }

}
