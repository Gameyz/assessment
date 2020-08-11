package com.zez.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Purple
 * @Date 2020/8/11
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableRowData {
    private String projectId;
    private String projectName;
    private Integer[] unitId;
    private String unitName;
    private Integer constructionNatureId;
    private String constructionNatureName;
    private Double planValue;
}
