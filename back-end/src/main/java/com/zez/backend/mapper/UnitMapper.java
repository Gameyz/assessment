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

    /**
     * 查询你所有的单位，并将二级单位对应着相应的一级单位上
     * @return unit实体类（二级单位列表有填充）
     */
    List<Unit> listAllUnit();


    /**
     * 通过id查询相应的一级单位
     * @param id 一级单位的id
     * @return 一级单位相应的实体类
     */
    Unit findUnitById(@Param("id")Integer id);

}
