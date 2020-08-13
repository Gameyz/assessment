package com.zez.backend.mapper;


import com.zez.backend.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Mapper
@Component
public interface ProjectMapper {
    /**
     * 根据idg更新项目
     * @param project 项目的实体类
     * @return 影响的行数
     */
    Integer updateProjectById(Project project);

    /**
     * 插入项目
     * @param project 项目的实体类
     * @return 影响的行数
     */
    Integer insertProject(Project project);

    /**
     * 批量插入项目
     * @param list 项目实体类列表
     * @return 影响的行数
     */
    Integer insertList(List<Project> list);
}
