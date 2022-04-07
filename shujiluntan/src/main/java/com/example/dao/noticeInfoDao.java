package com.example.dao;

import com.example.entity.noticeInfo;
import com.example.vo.noticeInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface noticeInfoDao extends Mapper<noticeInfo> {
    List<noticeInfoVo> findByName(@Param("name") String name);
    
    
    
}
