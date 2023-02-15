package uz.gc.productservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.gc.productservice.dto.ErrorDto;
import uz.gc.productservice.dto.ResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ExceptionHandlerResource {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> validationError(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest()
                        .body(ResponseDto.<Void>builder()
                                .message("Validation error")
                                .errors(e.getBindingResult().getFieldErrors()
                                        .stream()
                                        .map(f -> new ErrorDto(f.getField(), f.getDefaultMessage()))
                                        .toList())
                                .build());
    }
}
