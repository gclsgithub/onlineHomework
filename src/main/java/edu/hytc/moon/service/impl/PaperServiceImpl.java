package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.Paper;
import edu.hytc.moon.domain.Question;
import edu.hytc.moon.domain.QuestionPaper;
import edu.hytc.moon.mapper.PaperMapper;
import edu.hytc.moon.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<Paper> getAll() {
        return paperMapper.queryAll();
    }

    @Override
    public void addPaper(Paper paper) {
        paperMapper.addPaper(paper);
    }

    @Override
    public Paper getPaperById(Integer id) {
        return paperMapper.queryPaperById(id);
    }

    @Override
    public void editPaper(Paper paper) {
        paperMapper.editPaper(paper);
    }

    @Override
    public void deletePaperById(Integer id) {
        paperMapper.deletePaperById(id);
    }

    @Override
    public List<Paper> queryPaperNotAdvisor() {
        return paperMapper.queryPaperNotAdvisor();
    }

    @Override
    public List<Question> queryALlQuestionId() {
        return paperMapper.queryALlQuestionId();
    }

    @Override
    public List<QuestionPaper> paperQueryALlQuestionById(Integer id) {
        return paperMapper.paperQueryALlQuestionById(id);
    }

    @Override
    public List<Question> queryALlQuestionIdInPaperById(Integer paperId) {
        return paperMapper.queryALlQuestionIdInPaperById(paperId);
    }

    @Override
    public Paper queryPaperNameById(Integer id) {
        return paperMapper.queryPaperNameById(id);
    }

    @Override
    public void AddQuestionToPaperById(QuestionPaper questionPaper) {
        paperMapper.AddQuestionToPaperById(questionPaper);
    }

    @Override
    public void detachQuestionById(Integer qpId) {
        paperMapper.detachQuestionById(qpId);
    }

    @Override
    public int queryCountALlPaps() {
        return paperMapper.queryCountALlPaps();
    }

    @Override
    public List<QuestionPaper> paperQueryALlQuestionByIdOrderByType(Integer id) {
        return paperMapper.paperQueryALlQuestionByIdOrderByType(id);
    }
}
