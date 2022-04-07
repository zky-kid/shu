package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.MaterialsInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.MaterialsInfoComment;
import com.example.vo.MaterialsInfoCommentVo;
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
public class MaterialsInfoCommentService {

    @Resource
    private MaterialsInfoCommentDao MaterialsInfoCommentDao;

    public MaterialsInfoComment add(MaterialsInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        MaterialsInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        MaterialsInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(MaterialsInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        MaterialsInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public MaterialsInfoComment findById(Long id) {
        return MaterialsInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<MaterialsInfoCommentVo> findAll() {
        return MaterialsInfoCommentDao.findAllVo(null);
    }

    public PageInfo<MaterialsInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MaterialsInfoCommentVo> all = MaterialsInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<MaterialsInfoCommentVo> findByForeignId (Long id) {
        List<MaterialsInfoCommentVo> all = MaterialsInfoCommentDao.findByForeignId(id, 0L);
        for (MaterialsInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<MaterialsInfoCommentVo> children = new ArrayList<>(MaterialsInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
