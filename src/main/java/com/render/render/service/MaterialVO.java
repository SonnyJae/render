package com.render.render.service;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialVO {
    private Integer id; // 순번 (null 가능)

    @NotNull(message = "Category는 필수 입력 항목입니다.")
    private String category;    // 구분 (1.IBS, 2.오스템)

    @NotNull(message = "Code는 필수 입력 항목입니다.")
    private String code;    // 코드

    @NotNull(message = "Name는 필수 입력 항목입니다.")
    private String name;    // 제품명

    @NotNull(message = "Diameter는 필수 입력 항목입니다.")
    private Double diameter;    // 직경(mm)

    @NotNull(message = "Length는 필수 입력 항목입니다.")
    private Double length;  // 길이(mm)

    @NotNull(message = "Type는 필수 입력 항목입니다.")
    private String type;

    @NotNull(message = "Quantity는 필수 입력항목입니다.")
    private Integer quantity;   // 수량
}

