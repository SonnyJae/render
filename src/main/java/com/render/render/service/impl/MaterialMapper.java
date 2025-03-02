package com.render.render.service.impl;

import com.render.render.service.MaterialVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {
    List<MaterialVO> selectList(MaterialVO materialVO);

    MaterialVO selectMaterialProperty(Integer id);

    String updateQuantity(MaterialVO materialVO);

    int updateProperty(MaterialVO materialVO);

    String deleteProperty(Long id);

    int insertProperty(MaterialVO materialVO);
}
