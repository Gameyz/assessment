package com.zez.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "project_index",type = "project")
public class Project implements Serializable {

    @Id
    private String projectId;
    @Field(type = FieldType.Integer)
    private Integer constructionNatureId;

    @Field(type = FieldType.Keyword)
    private String projectName;
    @Field(type = FieldType.Integer)
    private Integer unitId;

    @Field(type = FieldType.Integer)
    private Integer subUnitId;

}
