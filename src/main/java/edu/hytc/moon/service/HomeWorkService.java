package edu.hytc.moon.service;

import edu.hytc.moon.domain.Exam;
import edu.hytc.moon.domain.HomeWork;

import java.util.List;

public interface HomeWorkService {

    //查看这个用户所有的家庭作业
    List<HomeWork> getAll(int seeId);

    HomeWork findHomeworkId(int homeworkId);

    List<HomeWork> findByOwnerId(int teacherId);

    int saveHomeWorkInfo(HomeWork homeWork);

    void deleteHomeWork(Integer homeworkId);

    int queryCountAllPas();
}
