package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.MaterialsInfo;
import com.example.service.MaterialsInfoService;
import com.example.vo.MaterialsInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/MaterialsInfo")
public class MaterialsInfoController {

    @Resource
    private NxSystemFileController nxSystemFileController;
    @Resource
    private MaterialsInfoService MaterialsInfoService;
    @PostMapping
    public Result<MaterialsInfo> add(@RequestBody MaterialsInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        info.setUserName(account.getName());
        info.setLevel(account.getLevel());
        info.setUploadUserId(account.getId());
        MaterialsInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        MaterialsInfo info = MaterialsInfoService.findById(id);
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能删除他人的记录");
        }
        MaterialsInfoService.delete(id);
        // 删除对应文件记录
        if (info.getFileId() != null) {
            nxSystemFileController.deleteFile(info.getFileId().toString());
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody MaterialsInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能修改他人的记录");
        }
        MaterialsInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<MaterialsInfoVo> detail(@PathVariable Long id) {
        MaterialsInfoVo info = MaterialsInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<MaterialsInfoVo>> all() {
        return Result.success(MaterialsInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<MaterialsInfoVo>> page(@PathVariable String name,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             HttpServletRequest request) {
        return Result.success(MaterialsInfoService.findPage(name, pageNum, pageSize));
    }

}
