package com.pokemon.pokemonTester.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class GeneralResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Object response;

    public GeneralResponseDto(
            HttpStatus httpCode,
            Object response,
            String message,
            String status
    ) {
        this.code = String.valueOf(httpCode.value());
        this.response = response;
        this.message = message;
        this.status = status;
    }

}
