package com.zez.backend.controller;


import cn.hutool.core.lang.UUID;
import com.zez.backend.common.CommonResult;
import com.zez.backend.elasticSearch.repository.PlanValueRepository;
import com.zez.backend.elasticSearch.repository.ProjectDataRepository;
import com.zez.backend.entity.PlanValue;
import com.zez.backend.entity.Project;
import com.zez.backend.service.IPlanValueService;
import com.zez.backend.service.IProjectService;
import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            @RequestParam(name = "unit", required = false) Integer unit,
            @RequestParam(name = "subUnit", required = false) Integer subUnit,
            @RequestParam(name = "constructionNatureId", required = false) Integer constructionNature,
            @RequestParam(name = "projectId", required = false) String projectId,
            @RequestParam(name = "projectName", required = false) String projectName,
            @RequestParam(name = "yearValue", required = false)String data){

        if(null == data){
            return CommonResult.fail(400,"年份为空");
        }

        String year = data.substring(0, 4);
        Page<PlanValue> values = planValueService.planValueSummary(projectName, unit, subUnit, constructionNature, projectId, year);

        return CommonResult.succ("KO", values);

    }


    /******************************************************************/
    @Autowired
    private PlanValueRepository planValueRepository;
    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Autowired
    private IProjectService projectService;

    @GetMapping("/test")
    public Object test(){
        return planValueRepository.findById("2020123456789012");
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        queryBuilder.addAggregation(AggregationBuilders.sum("plan_value"));
//        SearchRequest searchRequest = new SearchRequest();

//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery("plan_value","asdasd"));
//        searchRequest.source(searchSourceBuilder);
//        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        //return planValueRepository.existsById("asdasd");
        //return planValueRepository.index(new PlanValue("asdasd",25,"dasdsa","2020"));


        //return planValueRepository.findById("asdasd");

    }
    @GetMapping("/test02")
    public Object test02(){

        List<Project> projects = new ArrayList<>();
        List<PlanValue> planValues = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            String s = UUID.randomUUID().toString();
            s =  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
            String id = s.substring(0, 12);


            Project project = new Project(id, 2, "项目三" + i, 1, 2);
            PlanValue planValue1 = new PlanValue("2020" + id, ThreadLocalRandom.current().nextDouble(0,100), "2020", id);
            PlanValue planValue2 = new PlanValue("2019" + id, ThreadLocalRandom.current().nextDouble(0,100), "2019", id);
            PlanValue planValue3 = new PlanValue("2018" + id, ThreadLocalRandom.current().nextDouble(0,100), "2018", id);
            PlanValue planValue4 = new PlanValue("2017" + id, ThreadLocalRandom.current().nextDouble(0,100), "2017", id);

            projects.add(project);
            planValues.add(planValue1);
            planValues.add(planValue2);
            planValues.add(planValue3);
            planValues.add(planValue4);
        }
        Integer integer1 = projectService.insertList(projects);
        Integer integer = planValueService.insertList(planValues);
        return integer.toString()+" "+integer1.toString();
//        projects.add(new Project("123456789013",2,"项目三",1,2));
//        projects.add(new Project("123456789014",2,"项目三r",1,2));
//
//         return projectDataRepository.saveAll(projects);

        //return planValueService.insertPlanValue(new PlanValue("2020123456789012",53.0,"2020","123456789012"));
//        projectDataRepository.save(new Project("123456789013",2,"项目三",1,2));
//        projectDataRepository.save(new Project("123456789014",2,"项目三r",1,2));
//        projectDataRepository.save(new Project("123456789015",2,"项目三",1,2));
//        projectDataRepository.save(new Project("123456789016",2,"项目三d",1,2));
//        projectDataRepository.save(new Project("123456789017",2,"项目三f",1,2));
//        projectDataRepository.save(new Project("123456789018",2,"项目三f",1,2));
//        projectDataRepository.save(new Project("123456789019",2,"项目三g",1,2));
//        projectDataRepository.save(new Project("123456789020",2,"项目三h",1,2));
//        projectDataRepository.save(new Project("123456789021",2,"项目三h",1,2));
//        projectDataRepository.save(new Project("123456789022",2,"项目三",1,2));



        //planValueRepository.save(new PlanValue("2017" + "123456789022", ThreadLocalRandom.current().nextDouble(0,100), "2017", "123456789022"));
//
//        return "OK";

    }

    @GetMapping("/planValue")
    public CommonResult<Object> getPlanValue(){
        return CommonResult.succ("OK","OK");
    }

    @GetMapping("/test03")
    public Object test03(){
        projectService.insertProject(new Project("123456789013",2,"项目三",1,2));
        planValueService.insertPlanValue(new PlanValue("2017" + "123456789013", ThreadLocalRandom.current().nextDouble(0,100), "2017", "123456789013"));

        return "OK";
    }


}
