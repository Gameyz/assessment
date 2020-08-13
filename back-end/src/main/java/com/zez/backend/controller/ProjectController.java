package com.zez.backend.controller;


import com.zez.backend.common.CommonResult;
import com.zez.backend.common.PageTableResult;
import com.zez.backend.common.TableRowData;
import com.zez.backend.elasticSearch.repository.ProjectDataRepository;
import com.zez.backend.entity.Project;
import com.zez.backend.service.IConstructionNatureService;
import com.zez.backend.service.IProjectService;
import com.zez.backend.service.ISubUnitService;
import com.zez.backend.service.IUnitService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    private IProjectService projectService;
    @Autowired
    public void setProjectService(IProjectService projectService){
        this.projectService = projectService;
    }

    private ISubUnitService subUnitService;
    @Autowired
    public void setSubUnitService(ISubUnitService subUnitService){
        this.subUnitService = subUnitService;
    }

    private IUnitService unitservice;
    @Autowired
    public void setUnitservice(IUnitService unitservice){
        this.unitservice = unitservice;
    }

    private IConstructionNatureService constructionNatureService;
    @Autowired
    public void setConstructionNatureService(IConstructionNatureService constructionNatureService){
        this.constructionNatureService = constructionNatureService;
    }

    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Autowired
    public void setProjectDataRepository(ProjectDataRepository projectDataRepository){
        this.projectDataRepository = projectDataRepository;
    }

    @PutMapping("/update")
    public CommonResult<Object> updateProject(@RequestBody Project project){
        log.info("UPDATE "+project.toString());
        Integer integer = projectService.UpdateProjectById(project);
        return CommonResult.succ("OK!", integer);

    }

    @PostMapping("/add")
    public CommonResult<Object> addProject( @RequestBody Project project){


        log.info("SAVE "+project.toString());
        Integer integer = projectService.insertProject(project);
        return CommonResult.succ("ok!",integer);
    }

    @GetMapping("/projects/newId")
    public CommonResult<Object> getNewId(){
        String s = UUID.randomUUID().toString();
        s =  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        String id = s.substring(0, 12);

        return CommonResult.succ("ok",id);
    }

    @GetMapping("/projects")
    public CommonResult<Object>  getProjects(@RequestParam(name = "page", required = false, defaultValue = "0") String page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") String size,
                                            @RequestParam(name = "unit", required = false) Integer unit,
                                            @RequestParam(name = "subUnit", required = false) Integer subUnit,
                                            @RequestParam(name = "constructionNatureId", required = false) Integer constructionNature,
                                            @RequestParam(name = "projectId", required = false) String projectId,
                                            @RequestParam(name = "projectName", required = false) String projectName){


        int pageNum = Integer.parseInt(page);
        int pageSize = Integer.parseInt(size);

        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        if (null != unit){
            bqb.must(QueryBuilders.matchQuery("unitId",unit));
        }
        if (null != subUnit){
            bqb.must(QueryBuilders.matchQuery("subUnitId",subUnit));
        }
        if (null != constructionNature){
            bqb.must(QueryBuilders.matchQuery("constructionNatureId",constructionNature));
        }
        if (null != projectId){
            bqb.must(QueryBuilders.matchQuery("projectId",projectId));
        }
        if (null != projectName){
            bqb.must(QueryBuilders.matchQuery("projectName",projectName));
        }

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                .withPageable(PageRequest.of(pageNum, pageSize))
                .build();

        Page<Project> search = projectDataRepository.search(searchQuery);

        PageTableResult pageTableResult = new PageTableResult();
        pageTableResult.setTotalElements(search.getTotalElements());
        pageTableResult.setTotalPages(search.getTotalPages());

        List<TableRowData> list = new ArrayList<>();
        List<Project> content = search.getContent();

        for (Project project:
        content) {

            Integer[] unitId = new Integer[]{project.getUnitId(),project.getSubUnitId()};
            String allunitname = unitservice.findUnitNameByID(project.getUnitId())+"/"+subUnitService.findSubUnitNameById(project.getSubUnitId());
            String constructionNatureName = constructionNatureService.getConstructionNatureName(project.getConstructionNatureId());
            list.add(
                    new TableRowData(
                            project.getProjectId(),
                            project.getProjectName(),
                            unitId,
                            allunitname,
                            project.getConstructionNatureId(),
                            constructionNatureName,
                            0.0,
                            false,
                            0.0
                    )
            );
        }
        pageTableResult.setContent(list);
        return CommonResult.succ("KO", pageTableResult);


    }
}
