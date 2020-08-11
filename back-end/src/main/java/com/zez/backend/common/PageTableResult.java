package com.zez.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Purple
 * @Date 2020/8/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageTableResult {

    private Integer totalPages;
    private Long totalElements;
    private List<TableRowData> content;
}
