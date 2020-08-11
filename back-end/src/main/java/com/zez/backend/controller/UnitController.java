package com.zez.backend.controller;


import com.zez.backend.common.CommonResult;
import com.zez.backend.entity.Unit;
import com.zez.backend.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    public void setUnitService(IUnitService unitService){
        this.unitService = unitService;
    }

    private IUnitService unitService;

    @GetMapping("/idNameMap")
    public CommonResult<Object> getidNameMap(){
        return CommonResult.succ("OK","OK");
    }


    @GetMapping("/allUnit")
    public CommonResult<Object> listAllUnit(){
        List<Unit> unitList = unitService.listAllUnit();
        unitList.add(0, Unit.AllFlag());
        System.out.println(unitList.toString());
        return new CommonResult<>().success("OK!",unitList);
    }
}
