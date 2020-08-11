package com.zez.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
