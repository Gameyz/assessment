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
    Integer insertPlanValue(PlanValue planValue);
    Integer updatePlanValueById(String id, Double planValue);
    Integer insertList(List<PlanValue> planValues);
    Page<PlanValue> planValueSummary(String projectName,
                                     Integer unitId,
                                     Integer subUnitId,
                                     Integer constructionNatureId,
                                     String projectId,
                                     String yearValue);

}
