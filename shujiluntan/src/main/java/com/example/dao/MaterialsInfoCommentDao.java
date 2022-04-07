package com.example.dao;

import com.example.entity.MaterialsInfoComment;
import com.example.vo.MaterialsInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MaterialsInfoCommentDao extends Mapper<MaterialsInfoComment> {
    List<MaterialsInfoCommentVo> findAllVo(@Param("name") String name);
    List<MaterialsInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
