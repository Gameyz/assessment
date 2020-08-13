package com.zez.backend.mapper;


import com.zez.backend.entity.PlanValue;
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
public interface PlanValueMapper {

    /**
     * 根据id更新相应的行的计划值
     * @param id id
     * @param planValue 计划值
     * @return 影响的行数
     */
    Integer updatePlanValueById(@Param("id") String id, @Param("planValue") Double planValue);

    /**
     * 通过id查询计划值
     * @param id id
     * @return 计划值
     */
    PlanValue selectById(@Param("id") String id);

    /**
     * 插入计划值
     * @param planValue 计划值的实体类
     * @return 影响的行数
     */
    Integer insertPlanValue(PlanValue planValue);

    /**
     * 批量插入计划值
     * @param list 计划值的列表
     * @return 影响的行数
     */
    Integer insertList(List<PlanValue> list);

}
