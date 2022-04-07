package com.example.vo;

import com.example.entity.PostInfoComment;
import java.util.List;

public class PostInfoCommentVo extends PostInfoComment {

    private String foreignName;

    private List<PostInfoCommentVo> children;

    public List<PostInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<PostInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}