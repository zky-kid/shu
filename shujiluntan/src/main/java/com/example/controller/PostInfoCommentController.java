package com.example.controller;

import com.example.common.Result;
import com.example.entity.PostInfoComment;
import com.example.vo.PostInfoCommentVo;
import com.example.service.PostInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/PostInfoComment")
public class PostInfoCommentController {
    @Resource
    private PostInfoCommentService PostInfoCommentService;

    @PostMapping
    public Result<PostInfoComment> add(@RequestBody PostInfoComment commentInfo, HttpServletRequest request) {
        PostInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        PostInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody PostInfoComment commentInfo) {
        PostInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<PostInfoComment> detail(@PathVariable Long id) {
        PostInfoComment commentInfo = PostInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<PostInfoCommentVo>> all() {
        return Result.success(PostInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<PostInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(PostInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<PostInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(PostInfoCommentService.findByForeignId(id));
    }
}
