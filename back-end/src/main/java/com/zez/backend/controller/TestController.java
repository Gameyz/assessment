package com.zez.backend.controller;

import cn.hutool.core.lang.UUID;
import com.zez.backend.elasticSearch.repository.PlanValueRepository;
import com.zez.backend.elasticSearch.repository.ProjectDataRepository;
import com.zez.backend.entity.PlanValue;
import com.zez.backend.entity.Project;
import com.zez.backend.service.IPlanValueService;
import com.zez.backend.service.IProjectService;
import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Purple
 * @Date 2020/8/12
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private PlanValueRepository planValueRepository;
    @Autowired
    private ProjectDataRepository projectDataRepository;
    @Autowired
    private IProjectService projectService;

    @Autowired
    private IPlanValueService planValueService;


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
    }

    @GetMapping("/test03")
    public Object test03(){
        projectService.insertProject(new Project("123456789013",2,"项目三",1,2));
        planValueService.insertPlanValue(new PlanValue("2017" + "123456789013", ThreadLocalRandom.current().nextDouble(0,100), "2017", "123456789013"));

        return "OK";
    }
}
