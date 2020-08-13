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

    /**
     * 通过 项目id修改项目（es和mysql）
     * @param project 项目的实体类
     * @return 影响的行数
     */
    Integer updateProjectById(Project project);

    /**
     * 插入项目（es 和 mysql）
     * @param project 项目的实体类
     * @return 影响的行数
     */
    Integer insertProject(Project project);

    /**
     * 批量插入项目
     * @param Projects 项目的列表
     * @return 影响的行数
     */
    Integer insertList(List<Project> Projects);

}
