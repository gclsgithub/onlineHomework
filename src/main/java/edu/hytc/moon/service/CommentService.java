package edu.hytc.moon.service;

import edu.hytc.moon.domain.Readcomment;

import java.util.List;

public interface CommentService {
    List<Readcomment> findByHomeWorkId(Integer homeworkId);


    int deleteComment(Integer commentId);

    int saveReadComment(Readcomment readcomment);
}
