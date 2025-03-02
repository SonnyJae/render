package com.render.render.exception;  // 예외 처리는 보통 exception 패키지에 둡니다.

import com.render.render.service.JsonModel;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@RestControllerAdvice  // 이 어노테이션으로 전역 예외 처리를 담당
public class GlobalExceptionHandler {
    JsonModel jsonModel = new JsonModel();
    // 검증 오류가 발생했을 때 처리하는 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder msg=new StringBuilder();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        boolean isBlankError=false;//공백 오류인 경우
        boolean isNumberError=false;//공백 오류인 경우

        // 검증 오류 메시지 추출
        // 마지막 항목을 제외하고 ',' 추가
        for (int i = 0; i < allErrors.size(); i++) {
            String fieldName = ((FieldError) allErrors.get(i)).getField();
            String errorMessage = allErrors.get(i).getDefaultMessage();

            if (errorMessage != null) {
                // 공백 오류일 경우 우선 처리
                if (errorMessage.contains("필수 입력")) {
                    msg.setLength(0); // StringBuilder 초기화
                    msg.append(fieldName);
                    isBlankError=true;
                    break; // 공백 오류 발생 시 다른 오류 메시지 무시하고 종료
                }
                // 정규 표현식 오류일 경우
                if (errorMessage.contains("숫자만 입력") && isBlankError == false) {
                    msg.append(fieldName);
//                    isNumberError=true;//숫자오류로 처리
                }
                // 그 외의 오류 처리
                else {
                    msg.append(fieldName).append(" 오류: ").append(errorMessage);
                }
                // 마지막 항목이 아니면 ',' 추가
                if (i < allErrors.size() - 1) {
                    msg.append(", ");
                }
            }
        }//for문 끝
        jsonModel.setResultCode("400");
        if(isBlankError==true ){//공백오류난 경우 메세지 처리
            jsonModel.setResultMessage(msg.toString()+"는 필수입력 항목입니다.");
        }else if(isBlankError==false ){//정규표현식(숫자형태) 오류난 경우 메세지 처리
            jsonModel.setResultMessage(msg.toString());
        }

        return new ResponseEntity<>(jsonModel, HttpStatus.BAD_REQUEST);  // 400 상태 코드 반환
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<JsonModel> handleDataAccessException(DataAccessException ex) {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setResultCode("500");
        jsonModel.setResultMessage("데이터베이스 오류가 발생했습니다: " + ex.getMessage());
        return new ResponseEntity<>(jsonModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    // 추가적인 예외 처리 핸들러 여기에 추가 ㄱㄱ
}
