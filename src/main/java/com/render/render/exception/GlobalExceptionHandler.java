package com.render.render.exception;  // 예외 처리는 보통 exception 패키지에 둡니다.

import com.render.render.service.JsonModel;
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

        // 검증 오류 메시지 추출
        // 마지막 항목을 제외하고 ',' 추가
        for (int i = 0; i < allErrors.size(); i++) {
            String fieldName = ((FieldError) allErrors.get(i)).getField();
            String errorMessage = allErrors.get(i).getDefaultMessage();

            if (errorMessage != null) {
                msg.append(fieldName);
                // 마지막 항목이 아니면 ',' 추가
                if (i < allErrors.size() - 1) {
                    msg.append(", ");
                }
            }
        }
        jsonModel.setResultCode("400");
        jsonModel.setResultMessage(msg.toString()+"는 필수입력 항목입니다.");//forEach에서 쌓인 errorMessage를 한번에 jsonModel에 담음

        return new ResponseEntity<>(jsonModel, HttpStatus.BAD_REQUEST);  // 400 상태 코드 반환
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getConstraintViolations().forEach(violation -> {
//            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    // 추가적인 예외 처리 핸들러 여기에 추가 ㄱㄱ
}
