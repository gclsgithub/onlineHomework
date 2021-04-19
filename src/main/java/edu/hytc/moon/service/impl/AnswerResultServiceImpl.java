package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.mapper.AnswerResultMapper;
import edu.hytc.moon.service.AnswerResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerResultServiceImpl implements AnswerResultService {

    @Autowired
    AnswerResultMapper answerResultMapper;


    @Override
    public int deleteAnswersult(Integer answerResultId) {
        return answerResultMapper.deleteAnswerResultById(answerResultId);
    }

    @Override
    public int addNewAnswerResult(AnswerResult answerResult) {
        return answerResultMapper.inserNewAnswerResult(answerResult);
    }
}
