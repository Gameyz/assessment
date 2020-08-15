package com.zez.backend.entity;

import com.zez.backend.common.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * @Author Purple
 * @Date 2020/8/14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResquestBodyOfSelectLog {

    private String dateStart;
    private List<LogLevel> levels;
    private String ip;
    private Integer port;
    private String messages;
    private String className;
    private Integer page;
    private Integer pageSize;

}
