package com.zez.backend.common;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author Purple
 * @Date 2020/8/14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private Long totalElements;
    private List<Map<String, Object>> source;
}
