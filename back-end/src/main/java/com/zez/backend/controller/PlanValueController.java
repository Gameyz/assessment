package com.zez.backend.controller;


import com.zez.backend.common.CommonResult;
import com.zez.backend.entity.PlanValue;
import com.zez.backend.service.IPlanValueService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/planValue")
@Slf4j
public class PlanValueController {



    @Autowired
    public void setPlanValueService(IPlanValueService planValueService){
        this.planValueService = planValueService;
    }
    private IPlanValueService planValueService;


    @PutMapping("/update/{id}")
    public CommonResult<Object> updataPlanValue(@PathVariable("id") String id,@RequestParam Double planValue){
        Integer integer = planValueService.updatePlanValueById(id, planValue);
        return new CommonResult<>().success("OK",integer);
    }

    @PostMapping("/add")
    public CommonResult<Object> addPlanValue(PlanValue planValue){
        Integer integer = planValueService.insertPlanValue(planValue);
        return new CommonResult<>().success("OK",integer);

    }

    @GetMapping("/planValueSummary")
    public CommonResult<Object> getPlanValueSummary(
            @RequestParam(name = "unit", required = false) Integer unitId,
            @RequestParam(name = "subUnit", required = false) Integer subUnitId,
            @RequestParam(name = "constructionNatureId", required = false) Integer constructionNatureId,
            @RequestParam(name = "projectId", required = false) String projectId,
            @RequestParam(name = "projectName", required = false) String projectName,
            @RequestParam(name = "yearValue", required = false)String data){

        if(null == data){
            log.warn("Can not save get planValueSummary,because yearValue is null.");
            return CommonResult.fail(400,"年份为空");
        }

        String yearValue = data.substring(0, 4);

        AggregatedPage<PlanValue> search = (AggregatedPage)planValueService.planValueSummary(projectName, unitId, subUnitId, constructionNatureId, projectId, yearValue);

        Terms sum = (Terms)search.getAggregation("SUM");

        if(sum.getBuckets().isEmpty()){
            Map <String, Object> map = new HashMap<>();
            map.put("unit",unitId);
            map.put("subUnit",subUnitId);
            map.put("constructionNatureId",constructionNatureId);
            map.put("projectId",projectId);
            map.put("projectName",projectName);
            map.put("yearValue",yearValue);
            log.warn("can not find planValue of params:"+map.toString());
            return CommonResult.fail(404,"未找到");
        }else {
            return CommonResult.succ("OK",sum.getBuckets().get(0).getAggregations().getAsMap().get("sum_value"));
        }
    }


    @GetMapping("/planValue")
    public CommonResult<Object> getPlanValue(
            @RequestParam(name = "projectId", required = false) String projectId,
            @RequestParam(name = "yearValue", required = false)String data){

        if ( null == data){
            return new CommonResult<>(400,"NOt found",0.0);
        }
        String yearValue  = data.substring(0, 4);

        Page<PlanValue> value = this.planValueService.getPlanValue(projectId, yearValue);
        List<PlanValue> content = value.getContent();
        if(content.isEmpty()){
            return new CommonResult<>(400,"NOt found",0.0);
        }else {
            return CommonResult.succ("OK",content.get(0));
        }

    }

    @PostMapping("/planValue")
    public CommonResult<Object> postPlanValue(@RequestBody PlanValue planValue){

        if(planValue.getYearValue() == null){
            return CommonResult.fail(400,"项目id:"+planValue.getProjectId()+"未选择时间");
        }
        String yearValue = planValue.getYearValue().substring(0,4);
        String planValueId = yearValue+planValue.getProjectId();
        planValue.setYearValue(yearValue);
        planValue.setPlanValueId(planValueId);

        log.info("Save "+ planValue.toString());
        Integer integer = this.planValueService.insertPlanValue(planValue);
        return CommonResult.succ("OK",integer);
    }


}
