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

    /**
     * 根据id获取数据库中建设性质
     * @param id id字段
     * @return 建设性质
     */
    ConstructionNature getConstructionNatureById(@Param("id") Integer id);

    /**
     * 获取所有的建设性质
     * @return 建设性质列表
     */
    LinkedList<ConstructionNature> listAllConstructionNature();
}
