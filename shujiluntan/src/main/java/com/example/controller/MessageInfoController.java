package com.example.controller;

import com.example.common.Result;
import com.example.entity.MessageInfo;
import com.example.service.MessageInfoService;
import com.example.vo.MessageInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/MessageInfo")
public class MessageInfoController {
    @Resource
    private MessageInfoService MessageInfoService;

    @PostMapping
    public Result<MessageInfo> add(@RequestBody MessageInfoVo MessageInfo) {
        MessageInfoService.add(MessageInfo);
        return Result.success(MessageInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        MessageInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody MessageInfoVo MessageInfo) {
        MessageInfoService.update(MessageInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<MessageInfo> detail(@PathVariable Long id) {
        MessageInfo MessageInfo = MessageInfoService.findById(id);
        return Result.success(MessageInfo);
    }

    @GetMapping
    public Result<List<MessageInfoVo>> all() {
        return Result.success(MessageInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<MessageInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(MessageInfoService.findPage(name, pageNum, pageSize, request));
    }

}
