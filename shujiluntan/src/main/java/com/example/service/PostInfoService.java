package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.PostInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.PostInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.PostInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostInfoService {

    @Resource
    private PostInfoDao PostInfoDao;

    public PostInfo add(PostInfo info) {
        PostInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        PostInfoDao.deleteByPrimaryKey(id);
    }

    public void update(PostInfo info) {
        PostInfoDao.updateByPrimaryKeySelective(info);
    }

    public PostInfo findById(Long id) {
        return PostInfoDao.selectByPrimaryKey(id);
    }

    public List<PostInfoVo> findAll() {
        return PostInfoDao.findByName("all");
    }

    public PageInfo<PostInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<PostInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<PostInfoVo> findAllPage(HttpServletRequest request, String name) {
		return PostInfoDao.findByName(name);
    }

}
