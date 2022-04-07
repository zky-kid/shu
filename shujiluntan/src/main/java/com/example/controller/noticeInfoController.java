package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.noticeInfo;
import com.example.service.*;
import com.example.vo.noticeInfoVo;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/noticeInfo")
public class noticeInfoController {
    @Resource
    private noticeInfoService noticeInfoService;

    @PostMapping
    public Result<noticeInfo> add(@RequestBody noticeInfoVo noticeInfo) {
        noticeInfoService.add(noticeInfo);
        return Result.success(noticeInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        noticeInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody noticeInfoVo noticeInfo) {
        noticeInfoService.update(noticeInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<noticeInfo> detail(@PathVariable Long id) {
        noticeInfo noticeInfo = noticeInfoService.findById(id);
        return Result.success(noticeInfo);
    }

    @GetMapping
    public Result<List<noticeInfoVo>> all() {
        return Result.success(noticeInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<noticeInfoVo>> page(@PathVariable String name,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                               HttpServletRequest request) {
        return Result.success(noticeInfoService.findPage(name, pageNum, pageSize, request));
    }

    /**
    * 批量通过excel添加信息
    * @param file excel文件
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<noticeInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(noticeInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<noticeInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (noticeInfo info : resultList) {
                noticeInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
		row.put("name", "系统公告");
		row.put("content", "这是系统公告，管理员可以在后台修改");
		row.put("time", "TIME");

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=noticeInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}
