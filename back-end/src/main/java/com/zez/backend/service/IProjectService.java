package com.zez.backend.service;


import com.zez.backend.entity.Project;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface IProjectService {

    Integer UpdateProjectById(Project project);

    Integer insertProject(Project project);

    Integer insertList(List<Project> planValues);

}
