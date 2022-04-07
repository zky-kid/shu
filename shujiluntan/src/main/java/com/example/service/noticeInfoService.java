package com.example.service;

import com.example.dao.noticeInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.noticeInfo;
import com.example.vo.noticeInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class noticeInfoService {

    @Value("${authority.info}")
    private String authorityInfo;

    @Resource
    private noticeInfoDao noticeInfoDao;

    public noticeInfo add(noticeInfo noticeInfo) {
        noticeInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        noticeInfoDao.insertSelective(noticeInfo);
        return noticeInfo;
    }

    public void delete(Long id) {
        noticeInfoDao.deleteByPrimaryKey(id);
    }

    public void update(noticeInfo noticeInfo) {
        noticeInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        noticeInfoDao.updateByPrimaryKeySelective(noticeInfo);
    }

    public noticeInfo findById(Long id) {
        return noticeInfoDao.selectByPrimaryKey(id);
    }

    public List<noticeInfoVo> findAll() {
        return noticeInfoDao.findByName("all");
    }

    public PageInfo<noticeInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<noticeInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<noticeInfoVo> findAllPage(HttpServletRequest request, String name) {
		return noticeInfoDao.findByName(name);
    }

}
