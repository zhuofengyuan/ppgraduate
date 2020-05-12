package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.CommentDao;
import com.pp.ppgraduate.entity.CommentModel;
import com.pp.ppgraduate.service.CommentService;
import com.pp.ppgraduate.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    public boolean insertComment(CommentModel commentModel){
        commentModel.setCommentTime(DateUtil.getNowDate());
        return commentDao.insertComment(commentModel);
    }

    public boolean updateComment(CommentModel commentModel){
        return commentDao.updateComment(commentModel);
    }

    public boolean deleteComment(CommentModel commentModel){
        return commentDao.deleteComment(commentModel);
    }

    public CommentModel selectComment(CommentModel commentModel){
        return commentDao.selectComment(commentModel);
    }

    public List<CommentModel> selectAllComment(CommentModel commentModel){
        return commentDao.selectAllComment(commentModel);
    }
}
