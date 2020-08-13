package com.zez.backend.service;


import com.zez.backend.entity.PlanValue;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface IPlanValueService {
    /**
     * 插入计划值，同时向es和mysql中插入
     * @param planValue 计划值实体类
     * @return 影响的行数
     */
    Integer insertPlanValue(PlanValue planValue);

    /**
     *
     * @param id 更改计划值，同时向es和mysql中更改
     * @param planValue 计划值实体类
     * @return 影响的行数
     */
    Integer updatePlanValueById(String id, Double planValue);

    /**
     * 批量向es和mysql中插入计划值
     * @param planValues 计划值列表
     * @return 影响的行数
     */
    Integer insertList(List<PlanValue> planValues);

    /**
     * 从es中查询对应条件的汇总值
     * @param projectName 要查询的项目名称
     * @param unitId 要查询的一级单位id
     * @param subUnitId 要查询二级单位id
     * @param constructionNatureId 建设性质id
     * @param projectId 项目id
     * @param yearValue 年份
     * @return es rep返回的汇总值分页
     */
    Object planValueSummary(String projectName,
                            Integer unitId,
                            Integer subUnitId,
                            Integer constructionNatureId,
                            String projectId,
                            String yearValue);

    /**
     * 根据 项目的id和年份，获取对应的计划值
     * @param projectId 项目id
     * @param yearValue 年份
     * @return 对应的计划值分页
     */
    Page<PlanValue> getPlanValue(String projectId,
                        String yearValue);

}
