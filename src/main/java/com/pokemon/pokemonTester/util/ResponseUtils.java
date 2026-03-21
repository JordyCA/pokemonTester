package com.pokemon.pokemonTester.util;

import com.pokemon.pokemonTester.dto.ErrorResponse;
import com.pokemon.pokemonTester.dto.GeneralResponse;
import org.springframework.http.HttpStatus;

public final class ResponseUtils {

    public static GeneralResponse ErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponse responseError = new ErrorResponse();
        responseError.setCodigo(Integer.valueOf(String.valueOf(httpStatus.value())));
        responseError.setMessage(message);
        return new GeneralResponse(
                httpStatus, responseError, "", ""
        );

    }
}
