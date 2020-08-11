package com.zez.backend.mapper;


import com.zez.backend.entity.Unit;
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
public interface UnitMapper {

    List<Unit> listAllUnit();

    Unit findUnitById(@Param("id")Integer id);

}
