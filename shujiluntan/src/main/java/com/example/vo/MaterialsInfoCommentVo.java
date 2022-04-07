package com.example.vo;

import com.example.entity.MaterialsInfoComment;
import java.util.List;

public class MaterialsInfoCommentVo extends MaterialsInfoComment {

    private String foreignName;

    private List<MaterialsInfoCommentVo> children;

    public List<MaterialsInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<MaterialsInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}