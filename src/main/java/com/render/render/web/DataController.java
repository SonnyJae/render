package com.render.render.web;

import com.render.render.service.JsonModel;
import com.render.render.service.MaterialService;
import com.render.render.service.MaterialVO;
import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/material")
public class DataController {

    @Autowired
    MaterialService materialService;



//    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @GetMapping(value = "/selectAll")
    public JsonModel selectList() throws Exception {
        MaterialVO materialVO = new MaterialVO();
        JsonModel jsonModel = new JsonModel();
        List<MaterialVO> result = materialService.resultList(materialVO);
        jsonModel.putResultData("result",result);
//       System.out.println("json 결과 >>> "+result);
        return jsonModel;
    }
    @PutMapping(value="/updateQuantity")
    public ResponseEntity<JsonModel> updateQuantity(@RequestBody MaterialVO materialVO) throws Exception{
        JsonModel jsonModel = new JsonModel();
//        log.info("materialVO>>>>>>>>{}",materialVO);
        if(materialVO.getId()==null || materialVO.getQuantity()==null){
            String errMsg="";
            if(materialVO.getId()==null && materialVO.getQuantity()==null) {
                errMsg = "id, 수량";
            }else if(materialVO.getQuantity()==null){
                errMsg ="수량";
            }else if(materialVO.getId()==null){
                errMsg ="id";
            }
            jsonModel.setResultCode("400");
            jsonModel.setResultMessage(errMsg+"이 입력되지 않았습니다.");
            return new ResponseEntity(jsonModel,HttpStatus.BAD_REQUEST);
        }
        if(!materialVO.getQuantity().matches("^[0-9]+$")){
            jsonModel.setResultCode("400");
            jsonModel.setResultMessage("수량은 0 이상의 정수만 입력 가능합니다.");
            return new ResponseEntity(jsonModel,HttpStatus.BAD_REQUEST);
        }
        String name=materialService.updateQuantity(materialVO);
        if(name==null){
            jsonModel.setResultCode("404");
            jsonModel.setResultMessage("해당 id의 품목이 없습니다.");
            return new ResponseEntity(jsonModel,HttpStatus.NOT_FOUND);
        }

        String msg=name+" 의 속성을 변경했습니다.";

        jsonModel.setResultMessage(msg);
        jsonModel.setResultCode("200");
        log.info("updateQuantity 완료: id={}, name={}, quantity={}", materialVO.getId(), name, materialVO.getQuantity());

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }

    @PutMapping(value="/updateProperty")
    public ResponseEntity<JsonModel> updateProperty(@Valid @RequestBody MaterialVO materialVO) throws Exception{
        JsonModel jsonModel = new JsonModel();
//        log.info("materialVO>>>>>>>>{}",materialVO);
        if(materialVO.getId()==null){
            String errMsg="id";
            jsonModel.setResultCode("400");
            jsonModel.setResultMessage(errMsg+"를 식별할 수 없습니다.");
            return new ResponseEntity(jsonModel,HttpStatus.BAD_REQUEST);
        }
        Integer id=materialVO.getId();
        MaterialVO selectResult=materialService.selectMaterialProperty(id);
        if(selectResult==null){
            jsonModel.setResultCode("404");
            jsonModel.setResultMessage("해당 id의 품목이 없습니다.");
            return new ResponseEntity(jsonModel,HttpStatus.NOT_FOUND);
        }
        materialService.updateProperty(materialVO);
        String msg=selectResult.getName()+"의 속성을 변경했습니다.";

        jsonModel.setResultMessage(msg);
        jsonModel.setResultCode("200");
        log.info("updateProperty 완료: id={}, name={}", id, selectResult.getName());

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteProperty")
    public ResponseEntity<JsonModel> deleteProperty(@RequestParam("id") Long id) throws Exception{
        JsonModel jsonModel = new JsonModel();
        //id를 식별할 수 없을경우 예외로 던짐
        if(id==null){
            jsonModel.setResultMessage("id를 식별할 수 없습니다.");
            jsonModel.setResultCode("400");//삭제 오류 코드 : 400
            return new ResponseEntity(jsonModel, HttpStatus.BAD_REQUEST);
        }
//        log.info("materialVO>>>>>>>>{}",materialVO);

        String name=materialService.deleteProperty(id);
        if(name==null){
            jsonModel.setResultCode("404");
            jsonModel.setResultMessage("해당 id의 품목이 없습니다.");
            return new ResponseEntity(jsonModel, HttpStatus.NOT_FOUND);
        }
        jsonModel.setResultCode("200");//정상 삭제 코드 200
        jsonModel.setResultMessage(name+"삭제가 완료되었습니다.");
        log.info("deleteProperty 완료: id={}, name={}", id, name);

        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }

    @PostMapping(value="/insertProperty")
    public ResponseEntity<JsonModel> insertProperty(@Valid @RequestBody MaterialVO materialVO) throws Exception{
        JsonModel jsonModel = new JsonModel();
//        log.info("materialVO>>>>>>>>{}",materialVO);

        if(materialVO.getId()!=null){
            jsonModel.setResultMessage("파라미터 오류 : id 입력");
            jsonModel.setResultCode("400");//삭제 오류 코드 : 400
            return new ResponseEntity(jsonModel, HttpStatus.BAD_REQUEST);
        }
        
        int result=materialService.insertProperty(materialVO);

        log.info("insertProperty 완료: name={}, result={}", materialVO.getName(), result);
        String msg=materialVO.getName()+"를 추가했습니다.";
        jsonModel.setResultMessage(msg);
        jsonModel.setResultCode("200");//삭제 오류 코드 : 400
        return new ResponseEntity(jsonModel, HttpStatus.OK);
    }


}

