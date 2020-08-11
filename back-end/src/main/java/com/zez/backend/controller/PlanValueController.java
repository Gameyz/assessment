package com.zez.backend.controller;


import com.zez.backend.common.CommonResult;
import com.zez.backend.entity.PlanValue;
import com.zez.backend.service.IPlanValueService;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
            return CommonResult.fail(400,"年份为空");
        }

        String yearValue = data.substring(0, 4);
        AggregatedPage<PlanValue> search = (AggregatedPage)planValueService.planValueSummary(projectName, unitId, subUnitId, constructionNatureId, projectId, yearValue);

        Terms sum = (Terms)search.getAggregation("SUM");

        if(sum.getBuckets().isEmpty()){
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
        Integer integer = this.planValueService.insertPlanValue(planValue);
        return CommonResult.succ("OK",integer);
    }


}
