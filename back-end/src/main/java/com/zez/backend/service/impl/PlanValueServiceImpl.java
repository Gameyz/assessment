package com.zez.backend.service.impl;


import com.zez.backend.elasticSearch.repository.PlanValueRepository;
import com.zez.backend.entity.PlanValue;
import com.zez.backend.mapper.PlanValueMapper;
import com.zez.backend.service.IPlanValueService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Service
public class PlanValueServiceImpl implements IPlanValueService {

    @Autowired
    private PlanValueMapper planValueMapper;

    @Autowired
    private PlanValueRepository planValueRepository;




    @Override
    public Integer insertPlanValue(PlanValue planValue){
        planValueRepository.save(planValue);
        return planValueMapper.insertPlanValue(planValue);
    }

    @Override
    public Integer updatePlanValueById(String id, Double planValue){

        PlanValue value = planValueRepository.findById(id).get();
        value.setPlanValue(planValue);
        planValueRepository.save(value);

        return planValueMapper.updatePlanValueById(id,planValue);
    }


    @Override
    public Page<PlanValue> planValueSummary(String projectName,
                                            Integer unitId,
                                            Integer subUnitId,
                                            Integer constructionNatureId,
                                            String projectId,
                                            String yearValue){


        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        if (null != unitId){
            bqb.must(QueryBuilders.matchQuery("unitId",unitId));
        }
        if (null != subUnitId){
            bqb.must(QueryBuilders.matchQuery("subUnitId",subUnitId));
        }
        if (null != constructionNatureId){
            bqb.must(QueryBuilders.matchQuery("constructionNatureId",constructionNatureId));
        }
        if (null != projectId){
            bqb.must(QueryBuilders.matchQuery("projectId",projectId));
        }
        if (null != projectName){
            bqb.must(QueryBuilders.matchQuery("projectName",projectName));
        }


        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(JoinQueryBuilders.hasParentQuery("project",bqb,false));
        boolQuery.must(QueryBuilders.matchQuery("yearValue",yearValue));


        TermsAggregationBuilder aggregation = AggregationBuilders
                .terms("sum")
                .field("yearValue")
                .subAggregation(AggregationBuilders
                        .sum("sum_value")
                        .field("planValue"));

        aggregation.showTermDocCountError(true);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                .addAggregation(aggregation)
                .withPageable(PageRequest.of(0,1))
                .build();


        Page<PlanValue> search = planValueRepository.search(searchQuery);
        return search;



    }

    @Override
    public Integer insertList(List<PlanValue> planValues){
        planValueRepository.saveAll(planValues);
        return this.planValueMapper.insertList(planValues);
    }
}
