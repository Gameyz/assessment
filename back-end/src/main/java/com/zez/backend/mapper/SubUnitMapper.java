package com.zez.backend.mapper;


import com.zez.backend.entity.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
public interface SubUnitMapper {
    Unit findSubUnitByID(@Param("id") Integer id);
}
