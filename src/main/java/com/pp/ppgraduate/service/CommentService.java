package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.CommentModel;

import java.util.List;

public interface CommentService {
    public boolean insertComment(CommentModel commentModel);

    public boolean updateComment(CommentModel commentModel);

    public boolean deleteComment(CommentModel commentModel);

    public CommentModel selectComment(CommentModel commentModel);

    public List<CommentModel> selectAllComment(CommentModel commentModel);
}
