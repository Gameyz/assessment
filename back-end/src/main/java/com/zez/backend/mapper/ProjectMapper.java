package com.zez.backend.mapper;


import com.zez.backend.entity.PlanValue;
import com.zez.backend.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    Integer updateProjectById(Project project);
    Integer insertProject(Project project);

    Integer insertList(List<Project> list);
}
