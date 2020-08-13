package com.zez.backend.service.impl;


import com.zez.backend.entity.Unit;
import com.zez.backend.mapper.UnitMapper;
import com.zez.backend.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Service
public class UnitServiceImpl implements IUnitService {
    private final static String KEY_PREFIX = "Unit";

    private UnitMapper unitMapper;
    @Autowired
    public void setUnitMapper(UnitMapper unitMapper){
        this.unitMapper = unitMapper;
    }

    private RedisTemplate<String,Object> stringObjectRedisTemplate;
    @Autowired
    public void setStringObjectRedisTemplate(RedisTemplate<String, Object> stringObjectRedisTemplate){
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }



    @Override
    public List<Unit> listAllUnit(){
        return unitMapper.listAllUnit();
    }



    @Override
    public String findUnitNameById(Integer id){
        if(null == id){
            return "";
        }
        String key = KEY_PREFIX+id.toString();
        String re = (String) stringObjectRedisTemplate.opsForValue().get(key);

        if (null == re){
            re = unitMapper.findUnitById(id).getUnitName();
            stringObjectRedisTemplate.opsForValue().set(key,re);
        }
        return re;
    }
}
