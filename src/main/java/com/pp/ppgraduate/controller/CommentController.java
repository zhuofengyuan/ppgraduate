package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.CommentModel;
import com.pp.ppgraduate.service.CommentService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.border.EmptyBorder;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CommentService commentService;

    @ResponseBody
    @PostMapping("/insertComment")
    public Result insertComment(@RequestBody CommentModel commentModel){
        if (EmptyUtil.isEmpty(commentModel)){
            return Result.error("评论信息为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getCommentContent())){
            return Result.error("评论内容为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getGoodsId())){
            return Result.error("商品id为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getOpenId())){
            return Result.error("用户id为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getUserName())){
            return Result.error("用户姓名为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getAttitude())){
            return Result.error("态度为空");
        }
        try{
            boolean flag = commentService.insertComment(commentModel);
            if (flag==true){
                return Result.success("新增成功！");
            }
            else {
                return Result.error("新增失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "新增评论信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody CommentModel commentModel){
        if (EmptyUtil.isEmpty(commentModel)){
            return Result.error("评论信息为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getCommentContent())){
            return Result.error("评论内容为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getAttitude())){
            return Result.error("态度为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getCommentId())){
            return Result.error("评论id为空");
        }
        try{
            boolean flag = commentService.updateComment(commentModel);
            if (flag==true){
                return Result.success("更改成功！");
            }
            else {
                return Result.error("更改失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "更改评论信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody CommentModel commentModel){
        if (EmptyUtil.isEmpty(commentModel)){
            return Result.error("评论信息为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getCommentId())){
            return Result.error("评论id为空");
        }
        try{
            boolean flag = commentService.deleteComment(commentModel);
            if (flag==true){
                return Result.success("删除成功！");
            }
            else {
                return Result.error("删除失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除评论信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/selectComment")
    public Result selectComment(@RequestBody CommentModel commentModel){
        if (EmptyUtil.isEmpty(commentModel)){
            return Result.error("评论信息为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getCommentId())){
            return Result.error("评论id为空");
        }
        try{
            return Result.success(commentService.selectComment(commentModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询具体评论信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/selectAllComment")
    public Result selectAllComment(@RequestBody CommentModel commentModel){
        if (EmptyUtil.isEmpty(commentModel)){
            return Result.error("评论信息为空");
        }
        if (EmptyUtil.isEmpty(commentModel.getGoodsId())){
            return Result.error("商品id为空");
        }
        try{
            return Result.success(commentService.selectAllComment(commentModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询商品下评论信息Exception");
        }
    }
}
