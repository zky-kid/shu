package com.example.dao;

import com.example.entity.PostInfo;
import com.example.vo.PostInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PostInfoDao extends Mapper<PostInfo> {
    List<PostInfoVo> findByName(@Param("name") String name);
    
    
    
}
