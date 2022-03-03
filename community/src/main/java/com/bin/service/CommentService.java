package com.bin.service;

import com.bin.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> selectAllCommentsByEntity(Integer entityType, Integer entityId, int offset, int limit);

    int selectCountByEntity(Integer entityType, Integer entityId);
}