package com.pokemon.pokemonTester.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Integer codigo;
    private String message;
}
