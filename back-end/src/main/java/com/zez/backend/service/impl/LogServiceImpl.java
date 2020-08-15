package com.zez.backend.service.impl;

import com.zez.backend.common.LogLevel;
import com.zez.backend.common.PageResult;
import com.zez.backend.entity.ResquestBodyOfSelectLog;
import com.zez.backend.service.ILogService;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

/**
 * @Author Purple
 * @Date 2020/8/14
 */

@Slf4j
@Service
public class LogServiceImpl implements ILogService {
    public static final String INDEX_PREFIX = "elklog-";

    @Autowired
    public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate){

        this.elasticsearchTemplate = elasticsearchTemplate;

    }
    private ElasticsearchTemplate elasticsearchTemplate;

    public static Date transferDateFormat(String oldDateStr) {
        if (StringUtils.isBlank(oldDateStr)){
            return null;
        }
        Date date = null;
        Date date1 = null;
        String dateStr = null;
        try {
            //是空格+UTC
            dateStr = oldDateStr.replace("Z", " UTC");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            date1 = df.parse(dateStr);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date = df1.parse(date1.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public PageResult selectLogsByParams(ResquestBodyOfSelectLog bodyOfSelectLog){


        Date startDate = LogServiceImpl.transferDateFormat(bodyOfSelectLog.getDateStart());
        String index = LogServiceImpl.INDEX_PREFIX + new SimpleDateFormat("yyyy.MM.dd").format(startDate);
        BoolQueryBuilder bool = QueryBuilders.boolQuery();

        if (!StringUtil.isNullOrEmpty(bodyOfSelectLog.getClassName())){
            bool.must(QueryBuilders.matchPhrasePrefixQuery("logger_name",bodyOfSelectLog.getClassName()));
        }
        if (!StringUtil.isNullOrEmpty(bodyOfSelectLog.getMessages())){
            bool.must(QueryBuilders.matchQuery("message",bodyOfSelectLog.getMessages()));
        }
        if (!StringUtil.isNullOrEmpty(bodyOfSelectLog.getIp())){
            bool.must(QueryBuilders.matchPhrasePrefixQuery("host",bodyOfSelectLog.getIp()));
        }

        if (bodyOfSelectLog.getPort()!=null){
            bool.must(QueryBuilders.matchQuery("port",bodyOfSelectLog.getPort()));
        }

        if(bodyOfSelectLog.getLevels()!=null && !bodyOfSelectLog.getLevels().isEmpty()){
            BoolQueryBuilder query = QueryBuilders.boolQuery();
            for (LogLevel level:
                    bodyOfSelectLog.getLevels()) {
                query.should(QueryBuilders.matchQuery("level",level));
            }
            bool.must(query);
        }


        Client client = elasticsearchTemplate.getClient();
        SearchResponse response = client.prepareSearch(index)
                .setTypes("logs")
                .setQuery(bool)
                .setSize(bodyOfSelectLog.getPageSize())
                .setFrom((bodyOfSelectLog.getPage() - 1) * bodyOfSelectLog.getPageSize())
                .execute()
                .actionGet(1000);

        PageResult result = new PageResult();

        result.setTotalElements(response.getHits().getTotalHits());
        List<Map<String, Object>> sources = new LinkedList<>();
        for (SearchHit next : response.getHits()) {
            Map<String, Object> source = next.getSource();
            //source.put("date",source.get("@"))
            sources.add(source);
        }
        result.setSource(sources);
        return result;
    }
}
