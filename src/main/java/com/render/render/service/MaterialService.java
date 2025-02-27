package com.render.render.service;

import com.render.render.service.impl.MaterialMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    //전체 리스트를 조회한다
    @Transactional
    public List<MaterialVO> resultList(MaterialVO materialVO)
    {
        return materialMapper.selectList(materialVO);
    }

    //품목의 속성정보를 조회한다
    public MaterialVO selectMaterialProperty(Integer id){return materialMapper.selectMaterialProperty(id);}
    
    //수량을 update한다
    @Transactional
    public String updateQuantity(MaterialVO materialVO) {
        return materialMapper.updateQuantity(materialVO);
    }

    //품목의 속성을 update한다
    @Transactional
    public int updateProperty(MaterialVO materialVO) {
        return materialMapper.updateProperty(materialVO);
    }

    //품목을 삭제한다.
    @Transactional
    public String deleteProperty(MaterialVO materialVO) {
        return materialMapper.deleteProperty(materialVO);
    }

    //품목을 추가한다.
    @Transactional
    public int insertProperty(MaterialVO materialVO) {return materialMapper.insertProperty(materialVO);}
}
