package com.zez.backend.service.impl;


import com.zez.backend.mapper.SubUnitMapper;
import com.zez.backend.service.ISubUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Service
public class SubUnitServiceImpl implements ISubUnitService {

    private final static String KEY_PREFIX = "SubUnit";

    private SubUnitMapper subUnitMapper;
    @Autowired
    public void setSubUnitMapper(SubUnitMapper subUnitMapper){
        this.subUnitMapper = subUnitMapper;
    }



    private RedisTemplate<String,Object> stringObjectRedisTemplate;
    @Autowired
    public void setStringObjectRedisTemplate(RedisTemplate<String, Object> stringObjectRedisTemplate){
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }


    @Override
    public String findSubUnitNameByID(Integer id){
        if(null == id){
            return "";
        }
        String key = KEY_PREFIX+id.toString();
        String re = (String) stringObjectRedisTemplate.opsForValue().get(key);

        if (null == re){
            re = subUnitMapper.findSubUnitByID(id).getUnitName();
            stringObjectRedisTemplate.opsForValue().set(key,re);
        }
        return re;
    }
}
