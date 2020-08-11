package com.zez.backend.service.impl;


import com.zez.backend.elasticSearch.repository.ProjectDataRepository;
import com.zez.backend.entity.Project;
import com.zez.backend.mapper.ProjectMapper;
import com.zez.backend.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Override
    public Integer UpdateProjectById(Project project){
        projectDataRepository.save(project);
        return projectMapper.updateProjectById(project);
    }

    @Override
    public Integer insertProject(Project project){
        projectDataRepository.save(project);
        return projectMapper.insertProject(project);
    }

    @Override
    public Integer insertList(List<Project> planValues){
        this.projectDataRepository.saveAll(planValues);
        return this.projectMapper.insertList(planValues);
    }
}
