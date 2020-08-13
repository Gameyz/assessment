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
    /**
     * 通过id查二级单位
     * @param id 二级单位的id
     * @return 二级单位的实体类
     */
    Unit findSubUnitByID(@Param("id") Integer id);
}
