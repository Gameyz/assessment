package com.zez.backend.service;


import com.zez.backend.entity.Unit;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface IUnitService {

    /**
     * 获取所有的一级二级单位
     * @return 单位实体类
     */
    List<Unit> listAllUnit();


    /**
     * 通过id查询一级单位对应的名称
     * @param id 一级单位的id
     * @return 名称
     */
    String findUnitNameById(Integer id);
}
