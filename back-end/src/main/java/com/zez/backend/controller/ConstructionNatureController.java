package com.zez.backend.controller;


import com.zez.backend.common.CommonResult;
import com.zez.backend.entity.ConstructionNature;
import com.zez.backend.service.IConstructionNatureService;
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
@RequestMapping("/ConstructionNature")
public class ConstructionNatureController {

    private IConstructionNatureService constructionNatureService;

    @Autowired
    public void setConstructionNatureService(IConstructionNatureService constructionNatureService){
        this.constructionNatureService = constructionNatureService;
    }


    @GetMapping("/AllConstructionNature")
    public CommonResult<Object> test(){
        List<ConstructionNature> list = constructionNatureService.listAllConstructionNature();
        return new CommonResult<>().success("OK",list);

    }




}
