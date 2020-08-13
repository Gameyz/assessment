package com.zez.backend.service.impl;

import com.zez.backend.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @Author Purple
 * @Date 2020/8/14
 */
public class LogServiceImpl implements ILogService {
    @Autowired
    public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate){
        this.elasticsearchTemplate = elasticsearchTemplate;
    }
    private ElasticsearchTemplate elasticsearchTemplate;



}
