package com.render.render.service;

import com.render.render.service.impl.MaterialMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    public List<MaterialVO> resultList(MaterialVO materialVO) {
        return materialMapper.selectList(materialVO);
    }
}
