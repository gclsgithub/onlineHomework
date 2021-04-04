package edu.hytc.moon;

import edu.hytc.moon.domain.QuestionPaper;
import edu.hytc.moon.domain.RecordExam;
import edu.hytc.moon.mapper.PaperMapper;
import edu.hytc.moon.mapper.QuestionMapper;
import edu.hytc.moon.service.RecordService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@MapperScan("edu.hytc.moonNotSleep")
@SpringBootTest
class MoonNotSleepApplicationTests {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RecordService recordService;

    @Test
    void contextLoads() {
    }
    @Test
    public void test1(){
        List<QuestionPaper> questionPapers = paperMapper.paperQueryALlQuestionById(1);
        System.out.println(questionPapers);
    }

    @Test
    public void test2(){
//        List<QuestionCount> questionCounts = questionMapper.queryNumOfQuestionType();
        List<Map> maps = questionMapper.queryNumOfQuestionType();

//        Map<Integer, String> integerStringMap = questionMapper.queryNumOfQuestionType();
//        System.out.println(integerStringMap);
        System.out.println(maps);
    }
    @Test
    public void test3(){
        System.out.println("+++++++++++++++++++++++++++++++++");
                 System.out.println("List转字符串");
                 List<String> list1 = new ArrayList<String>();
                list1.add("1");
                 list1.add("2");
                list1.add("3");
                 String ss = String.join(",", list1);
                 System.out.println(ss);
                 System.out.println("+++++++++++++++++++++++++++++++++");
                System.out.println("字符串转List");
                 List<String> listString = Arrays.asList(ss.split(","));
                 for (String string : listString) {
                         System.out.println(string);
                    }
                System.out.println("+++++++++++++++++++++++++++++++++");

    }
    @Test
    public void test4(){
        RecordExam recordExam=new RecordExam();
        recordExam.setClaId(1);
        recordExam.setExaName("java综合测试");
        System.out.println(recordExam);
        int i = recordService.queryAllScore(recordExam);
        System.out.println(i);
    }

}
