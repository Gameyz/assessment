package com.zez.backend.entity;


import lombok.*;

import java.io.Serializable;
import java.util.List;

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
public class Unit implements Serializable {

    private Integer unitId;

    private String unitName;

    public Unit(Integer unitId, String unitName){
        this.unitId = unitId;
        this.unitName = unitName;
    }

    private Integer upperLevelId;

    List<Unit> subUnits;

    public static Unit AllFlag(){
        return new Unit(-1,"全部");
    }


}
