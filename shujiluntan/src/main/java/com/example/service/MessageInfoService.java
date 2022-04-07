package com.example.service;

import com.example.dao.MessageInfoDao;
import com.example.entity.MessageInfo;
import com.example.vo.MessageInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageInfoService {

    @Resource
    private MessageInfoDao MessageInfoDao;

    public MessageInfo add(MessageInfo MessageInfo) {
        MessageInfoDao.insertSelective(MessageInfo);
        return MessageInfo;
    }

    public void delete(Long id) {
        MessageInfoDao.deleteByPrimaryKey(id);
    }

    public void update(MessageInfo MessageInfo) {
        MessageInfoDao.updateByPrimaryKeySelective(MessageInfo);
    }

    public MessageInfo findById(Long id) {
        return MessageInfoDao.selectByPrimaryKey(id);
    }

    public List<MessageInfoVo> findAll() {
        List<MessageInfoVo> all = MessageInfoDao.findByParentId(0L);
        for (MessageInfoVo MessageInfoVo : all) {
            Long id = MessageInfoVo.getId();
            List<MessageInfoVo> children = new ArrayList<>(MessageInfoDao.findByParentId(id));
            MessageInfoVo.setChildren(children);
        }
        return all;
    }

    public PageInfo<MessageInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<MessageInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<MessageInfoVo> findAllPage(HttpServletRequest request, String name) {
		return MessageInfoDao.findByName(name);
    }

}
