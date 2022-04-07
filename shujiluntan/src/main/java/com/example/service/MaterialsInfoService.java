package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.MaterialsInfoDao;
import com.example.entity.MaterialsInfo;
import com.example.vo.MaterialsInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialsInfoService {

    @Resource
    private MaterialsInfoDao MaterialsInfoDao;

    public MaterialsInfo add(MaterialsInfo info) {
        MaterialsInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        MaterialsInfoDao.deleteByPrimaryKey(id);
    }

    public void update(MaterialsInfo info) {
        MaterialsInfoDao.updateByPrimaryKeySelective(info);
    }

    public MaterialsInfoVo findById(Long id) {
        List<MaterialsInfoVo> list = MaterialsInfoDao.findByNameAndId(null, id);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return new MaterialsInfoVo();
    }

    public List<MaterialsInfoVo> findAll() {
        return MaterialsInfoDao.findByNameAndId("all", null);
    }

    public PageInfo<MaterialsInfoVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MaterialsInfoVo> info = MaterialsInfoDao.findByNameAndId(name, null);
        return PageInfo.of(info);
    }
}
