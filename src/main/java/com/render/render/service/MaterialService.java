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

    @Transactional
    public List<MaterialVO> resultList(MaterialVO materialVO)
    {
        return materialMapper.selectList(materialVO);
    }
    @Transactional
    public int updateProperty(MaterialVO materialVO) {
        return materialMapper.updateProperty(materialVO);
    }
    @Transactional
    public String deleteProperty(MaterialVO materialVO) {
        return materialMapper.deleteProperty(materialVO);
    }

    @Transactional
    public int insertProperty(MaterialVO materialVO) {
        return materialMapper.insertProperty(materialVO);
    }
}
