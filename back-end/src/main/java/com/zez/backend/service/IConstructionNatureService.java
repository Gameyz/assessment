package com.zez.backend.service;


import com.zez.backend.entity.ConstructionNature;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface IConstructionNatureService {
    /**
     * 返回所有的建设性质
     * @return 建设性质的List
     */
    List<ConstructionNature> listAllConstructionNature();

    /**
     * 通过id查找建设性质
     * @param id 建设性质的id
     * @return 建设性质的名称
     */
    String getConstructionNatureName(Integer id);
}
