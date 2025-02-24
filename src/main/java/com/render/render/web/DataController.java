package com.render.render.web;

import com.render.render.service.JsonModel;
import com.render.render.service.MaterialService;
import com.render.render.service.MaterialVO;
import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/material")
public class DataController {

    @Autowired
    MaterialService materialService;

    JsonModel jsonModel = new JsonModel();

//    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @GetMapping(value = "/selectAll")
    public JsonModel selectList() throws Exception {
        MaterialVO materialVO = new MaterialVO();
//        JsonModel jsonModel = new JsonModel();
        List<MaterialVO> result = materialService.resultList(materialVO);
        jsonModel.putResultData("result",result);
//       System.out.println("json 결과 >>> "+result);
        return jsonModel;
    }
    @PutMapping(value="/updateProperty")
    public ResponseEntity<JsonModel> updateProperty(@Valid @RequestBody MaterialVO materialVO) throws Exception{

//        log.info("materialVO>>>>>>>>{}",materialVO);

        int result=materialService.updateProperty(materialVO);
        jsonModel.setResultCode("200");
        System.out.println("update 결과 >>> "+result);

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteProperty")
    public ResponseEntity<JsonModel> deleteProperty(@RequestBody MaterialVO materialVO) throws Exception{
        //id를 식별할 수 없을경우 예외로 던짐
        if(materialVO.getId()==null){
            jsonModel.setResultMessage("선택항목을 식별할 수 없습니다.");
            jsonModel.setResultCode("400");//삭제 오류 코드 : 400
            return new ResponseEntity(jsonModel, HttpStatus.BAD_REQUEST);
        }
//        log.info("materialVO>>>>>>>>{}",materialVO);

        String name=materialService.deleteProperty(materialVO);
        String msg="";
        if(name !=null){
            msg=name+"삭제가 완료되었습니다.";
            jsonModel.setResultCode("200");//정상 삭제 코드 200
            jsonModel.setResultMessage(msg);
        }
        System.out.println("delete 결과 >>> "+name);

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }

    @PostMapping(value="/insertProperty")
    public ResponseEntity<JsonModel> insertProperty(@Valid @RequestBody MaterialVO materialVO) throws Exception{
//        log.info("materialVO>>>>>>>>{}",materialVO);

        int result=materialService.insertProperty(materialVO);

        System.out.println("insert 결과 >>> "+result);

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }


}

