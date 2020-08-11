package com.zez.backend.elasticSearch.repository;

import com.zez.backend.entity.PlanValue;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author Purple
 * @Date 2020/8/6
 */
public interface PlanValueRepository extends ElasticsearchRepository<PlanValue,String> {

}
