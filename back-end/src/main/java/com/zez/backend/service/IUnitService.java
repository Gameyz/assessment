package com.zez.backend.service;


import com.zez.backend.entity.Unit;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
public interface IUnitService {

    List<Unit> listAllUnit();

    Map<Integer,String> getIdNameMap();

    String findUnitNameByID(Integer id);
}
