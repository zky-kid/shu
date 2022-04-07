package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.PostInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.PostInfoComment;
import com.example.vo.PostInfoCommentVo;
import com.example.entity.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostInfoCommentService {

    @Resource
    private PostInfoCommentDao PostInfoCommentDao;

    public PostInfoComment add(PostInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        PostInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        PostInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(PostInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        PostInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public PostInfoComment findById(Long id) {
        return PostInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<PostInfoCommentVo> findAll() {
        return PostInfoCommentDao.findAllVo(null);
    }

    public PageInfo<PostInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PostInfoCommentVo> all = PostInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<PostInfoCommentVo> findByForeignId (Long id) {
        List<PostInfoCommentVo> all = PostInfoCommentDao.findByForeignId(id, 0L);
        for (PostInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<PostInfoCommentVo> children = new ArrayList<>(PostInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
