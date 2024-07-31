package com.jdh.springSecurityJwt.api.common.response.entity;

import com.jdh.springSecurityJwt.api.common.response.enums.ResponseEnum;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponseEntity<T extends Object> {
    private final String result;
    private final String msg;
    private final T data;

    @Builder
    public ApiResponseEntity(String result, String msg, T data) {
        if(result == null || result.isEmpty())
            this.result = ResponseEnum.OK.getResult();
        else
            this.result = result;

        if(msg == null || msg.isEmpty())
            this.msg = ResponseEnum.OK.getMessage();
        else
            this.msg = msg;

        this.data = data;
    }

    @Builder
    public ApiResponseEntity(T data) {
        this.result = ResponseEnum.OK.getResult();
        this.msg = ResponseEnum.OK.getMessage();
        this.data = data;
    }

    /**
     * success static factory method (not return data)
     */
    public static ResponseEntity<ApiResponseEntity> successResponseEntity() {
        return ResponseEntity.ok(
                ApiResponseEntity.builder()
                        .msg("요청 성공")
                        .build()
        );
    }

    /**
     * success static factory method (return data)
     */
    public static <T> ResponseEntity<ApiResponseEntity> successResponseEntity(T data) {
        return ResponseEntity.ok(
                ApiResponseEntity.builder()
                        .data(data)
                        .msg("요청 성공")
                        .build()
        );
    }

    /**
     * fail static factory method (not return data)
     */
    public static <T> ResponseEntity<ApiResponseEntity> failResponseEntity(String msg) {
        return ResponseEntity.ok(
                ApiResponseEntity.builder()
                        .result(ResponseEnum.FAIL.getResult())
                        .msg(msg != "요청 실패" ? msg : ResponseEnum.FAIL.getMessage())
                        .build()
        );
    }

    /**
     * fail static factory method (return data)
     */
    public static <T> ResponseEntity<ApiResponseEntity> failResponseEntity(T data, String msg) {
        return ResponseEntity.ok(
                ApiResponseEntity.builder()
                        .result(ResponseEnum.FAIL.getResult())
                        .msg(msg != "요청 실패" ? msg : ResponseEnum.FAIL.getMessage())
                        .data(data)
                        .build()
        );
    }
}
