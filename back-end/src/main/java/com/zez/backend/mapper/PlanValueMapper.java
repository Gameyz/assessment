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

    Integer updatePlanValueById(@Param("id") String id, @Param("planValue") Double planValue);
    PlanValue selectById(@Param("id") String id);
    Integer insertPlanValue(PlanValue planValue);
    Integer insertList(List<PlanValue> list);

}
