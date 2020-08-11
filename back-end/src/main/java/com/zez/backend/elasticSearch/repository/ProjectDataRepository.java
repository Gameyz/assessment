package com.zez.backend.elasticSearch.repository;


import com.zez.backend.entity.Project;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author Purple
 * @Date 2020/8/10
 */
public interface ProjectDataRepository extends ElasticsearchRepository<Project,String> {

}
