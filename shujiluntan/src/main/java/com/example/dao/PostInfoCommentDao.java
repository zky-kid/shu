package com.example.dao;

import com.example.entity.PostInfoComment;
import com.example.vo.PostInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PostInfoCommentDao extends Mapper<PostInfoComment> {
    List<PostInfoCommentVo> findAllVo(@Param("name") String name);
    List<PostInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
