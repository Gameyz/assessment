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
    List<ConstructionNature> listAllConstructionNature();
    String getConstructionNatureName(Integer id);
}
