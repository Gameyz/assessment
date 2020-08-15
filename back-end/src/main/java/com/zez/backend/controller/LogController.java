package com.zez.backend.controller;

import com.zez.backend.common.CommonResult;
import com.zez.backend.common.LogLevel;
import com.zez.backend.common.PageResult;
import com.zez.backend.entity.ResquestBodyOfSelectLog;
import com.zez.backend.exception.NoTimeInputException;
import com.zez.backend.service.ILogService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * @Author Purple
 * @Date 2020/8/14
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    public void setLogService(ILogService logService){
        this.logService = logService;
    }
    private ILogService logService;

    @PostMapping("/select")
    public CommonResult<Object> selectLogs(@RequestBody ResquestBodyOfSelectLog bodyOfSelectLog) throws NoTimeInputException{

        if (StringUtil.isNullOrEmpty(bodyOfSelectLog.getDateStart())){
            throw new NoTimeInputException("没有选择时间");
        }

        if(bodyOfSelectLog.getPage()==null){
            bodyOfSelectLog.setPage(1);
        }
        if(bodyOfSelectLog.getPageSize()==null) {
            bodyOfSelectLog.setPageSize(100);
        }

        log.info("Select log by"+bodyOfSelectLog.toString());
        PageResult result = this.logService.selectLogsByParams(bodyOfSelectLog);
        return CommonResult.succ("OK",result);
    }


}
