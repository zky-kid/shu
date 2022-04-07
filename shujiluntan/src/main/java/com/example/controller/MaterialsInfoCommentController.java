package com.example.controller;

import com.example.common.Result;
import com.example.entity.MaterialsInfoComment;
import com.example.vo.MaterialsInfoCommentVo;
import com.example.service.MaterialsInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/MaterialsInfoComment")
public class MaterialsInfoCommentController {
    @Resource
    private MaterialsInfoCommentService MaterialsInfoCommentService;

    @PostMapping
    public Result<MaterialsInfoComment> add(@RequestBody MaterialsInfoComment commentInfo, HttpServletRequest request) {
        MaterialsInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        MaterialsInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody MaterialsInfoComment commentInfo) {
        MaterialsInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<MaterialsInfoComment> detail(@PathVariable Long id) {
        MaterialsInfoComment commentInfo = MaterialsInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<MaterialsInfoCommentVo>> all() {
        return Result.success(MaterialsInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<MaterialsInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(MaterialsInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<MaterialsInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(MaterialsInfoCommentService.findByForeignId(id));
    }
}
