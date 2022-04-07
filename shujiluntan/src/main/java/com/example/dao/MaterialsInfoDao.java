package com.example.dao;

import com.example.entity.MaterialsInfo;
import com.example.entity.MaterialsInfo;
import com.example.vo.MaterialsInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MaterialsInfoDao extends Mapper<MaterialsInfo> {
    List<MaterialsInfoVo> findByNameAndId(@Param("name") String name, @Param("id") Long id);
}
