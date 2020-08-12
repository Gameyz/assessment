package com.zez.backend.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

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
@Document(indexName = "project_index",type = "plan_value")
public class PlanValue implements Serializable {


    @Id
    private String planValueId;

    @Field(type = FieldType.Double)
    private Double planValue;

    @Field(type = FieldType.Keyword)
    private String yearValue;

    @Parent(type = "project")
    @Field(type = FieldType.Keyword)
    private String projectId;

}
