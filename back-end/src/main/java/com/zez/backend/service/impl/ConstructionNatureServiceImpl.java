package com.zez.backend.service.impl;

import com.zez.backend.entity.ConstructionNature;
import com.zez.backend.mapper.ConstructionNatureMapper;
import com.zez.backend.service.IConstructionNatureService;
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
public class ConstructionNatureServiceImpl implements IConstructionNatureService {

    /**
     * ConstructionNature Redis存储的key前缀
     */
    private final static String KEY_PREFIX = "ConstructionNature";

    private ConstructionNatureMapper constructionNatureMapper;
    @Autowired
    public void setConstructionNatureMapper(ConstructionNatureMapper constructionNatureMapper){
        this.constructionNatureMapper = constructionNatureMapper;
    }

    private RedisTemplate<String, Object> stringObjectRedisTemplate;
    @Autowired
    public void setStringObjectRedisTemplate(RedisTemplate<String, Object> stringObjectRedisTemplate){
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }


    @Override
    public List<ConstructionNature> listAllConstructionNature(){
        return constructionNatureMapper.listAllConstructionNature();
    }

    @Override
    public String getConstructionNatureName(Integer id){
        if(null == id){
            return "";
        }
        String key = KEY_PREFIX+id.toString();
        String re = (String) stringObjectRedisTemplate.opsForValue().get(key);

        if (null == re){
            re = constructionNatureMapper.getConstructionNatureById(id).getConstructionNatureName();
            stringObjectRedisTemplate.opsForValue().set(key,re);
        }
        return re;
    }

}
