package com.render.render.service;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
//@Alias("MaterialVO")
public class MaterialVO {
    private Integer id; //순번
    private String category;    //구분(1.IBS, 2.오스템)
    private String code;    //코드
    private String name;    //제품명
    private Double diameter;    //직경(mm)
    private Double length;  //길이(mm)

}
