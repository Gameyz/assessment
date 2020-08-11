package com.zez.backend.entity;


import com.alibaba.druid.filter.AutoLoad;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Purple
 * @since 2020-08-05
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionNature implements Serializable {

    private Integer constructionNatureId;

    private String constructionNatureName;


}
