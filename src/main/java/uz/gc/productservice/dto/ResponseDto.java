package uz.gc.productservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;
    private List<ErrorDto> errors;
}
