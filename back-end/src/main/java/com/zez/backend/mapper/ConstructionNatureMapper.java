package com.zez.backend.mapper;


import com.zez.backend.entity.ConstructionNature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

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
public interface ConstructionNatureMapper {

    ConstructionNature getConstructionNatureById(@Param("id") Integer id);
    LinkedList<ConstructionNature> listAllConstructionNature();
}
