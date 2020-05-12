package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.CommentModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    public boolean insertComment(CommentModel commentModel);

    public boolean updateComment(CommentModel commentModel);

    public boolean deleteComment(CommentModel commentModel);

    public CommentModel selectComment(CommentModel commentModel);

    public List<CommentModel> selectAllComment(CommentModel commentModel);
}
