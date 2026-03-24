package com.pokemon.pokemonTester.util;

import com.pokemon.pokemonTester.dto.ErrorResponseDto;
import com.pokemon.pokemonTester.dto.GeneralResponseDto;
import org.springframework.http.HttpStatus;

public final class ResponseUtils {

    public static GeneralResponseDto ErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponseDto responseError = new ErrorResponseDto();
        responseError.setCodigo(Integer.valueOf(String.valueOf(httpStatus.value())));
        responseError.setMessage(message);
        return new GeneralResponseDto(
                httpStatus, responseError, "", ""
        );

    }
}
