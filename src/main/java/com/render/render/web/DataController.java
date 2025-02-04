package com.render.render.web;

import com.render.render.service.JsonModel;
import com.render.render.service.MaterialService;
import com.render.render.service.MaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/selectList")
public class DataController {

    @Autowired
    MaterialService materialService;

    @GetMapping(value = "/all")
    public List<MaterialVO> selectList() throws Exception {
            MaterialVO materialVO = new MaterialVO();
            materialVO.setCategory("2");//임시로 오스템
            List<MaterialVO> result = materialService.resultList(materialVO);
            //            jsonModel.putResultData("result",result);
            System.out.println("json 결과 >>> "+result);
            return result;
    }
}
