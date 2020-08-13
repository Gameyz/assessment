package com.zez.backend.service;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface ISubUnitService {
    /**
     * 通过id查询二级单位名称
     * @param id 二级单位id
     * @return 二级单位名称
     */
    String findSubUnitNameById(Integer id);

}
