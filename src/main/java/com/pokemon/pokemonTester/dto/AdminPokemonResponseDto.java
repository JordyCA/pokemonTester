package com.pokemon.pokemonTester.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminPokemonResponseDto {

    @Getter
    @Setter
    public static class ResponsePokemonDetailModel {
        private Integer id;
        private List<AdminPokemonResponseDto.ResponsePokemonFormsModel> forms;
        private Integer baseexperience;
        private Integer weight;
        private Integer height;
    }

    @Getter
    @Setter
    public static class ResponsePokemonFormsModel {
        private String name;
    }

    @Getter
    @Setter
    public static class PokemonDetailModel {
        private Integer id;
        private String name;
        @JsonProperty("base_experience")
        private Integer baseexperience;
        private Integer weight;
        private Integer height;
    }
}
