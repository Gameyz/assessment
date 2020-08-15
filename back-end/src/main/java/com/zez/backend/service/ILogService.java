package com.zez.backend.service;

import com.zez.backend.common.PageResult;
import com.zez.backend.entity.ResquestBodyOfSelectLog;


/**
 * @Author Purple
 * @Date 2020/8/14
 */

public interface ILogService {

    /**
     *
     * 根据传入的条件查询相应的日志
     * @param bodyOfSelectLog 查询条件
     * @return 相应的对象
     */
    PageResult selectLogsByParams(ResquestBodyOfSelectLog bodyOfSelectLog);
}
