package com.banks.loans.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

//for swagger ui
@Schema(
        name = "Generic Response",
        description = "Generic Response for all apis"
)
@Data @NoArgsConstructor @AllArgsConstructor
public class ResponseDTO<T>{
    @Schema(
            description = "status of the api request"
    )
    private HttpStatus status;

    @Schema(
            description = "response message of the api request"
    )
    private String message;

    @Schema(
            description = "response body of the api request"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDTO(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
