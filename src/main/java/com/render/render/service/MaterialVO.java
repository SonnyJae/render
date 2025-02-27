package com.render.render.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MaterialVO {
    private Integer id; // 순번 (null 가능)

    @NotBlank(message = "Category는 필수 입력 항목입니다.")
    private String category;    // 구분 (1.IBS, 2.오스템)

    @NotBlank(message = "Code는 필수 입력 항목입니다.")
    private String code;    // 코드

    @NotBlank(message = "Name는 필수 입력 항목입니다.")
    private String name;    // 제품명

    @NotBlank(message = "Diameter는 필수 입력 항목입니다.")
    @Pattern(regexp = "^([0-9]{1,3}(\\.[0-9]{1,2})?)$", message = "Diameter는 0.00 이상 999.99 이하의 숫자여야 합니다.")
    private String diameter;    // 직경(mm)

    @NotBlank(message = "Length는 필수 입력 항목입니다.")
    @Pattern(regexp = "^([0-9]{1,3}(\\.[0-9]{1,2})?)$", message = "Diameter는 0.00 이상 999.99 이하의 숫자여야 합니다.")
    private String length;  // 길이(mm)

    @NotBlank(message = "Quantity는 필수 입력항목입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "Quantity는 숫자만 입력 가능합니다.")
    private String quantity;   // 수량
}

