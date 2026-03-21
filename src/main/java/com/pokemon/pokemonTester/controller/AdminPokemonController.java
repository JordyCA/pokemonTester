package com.pokemon.pokemonTester.controller;

import com.pokemon.pokemonTester.common.ApiPath;
import com.pokemon.pokemonTester.dto.GeneralResponse;
import com.pokemon.pokemonTester.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.pokemon.pokemonTester.dto.AdminPokemonResponseDto.PokemonDetailModel;
import com.pokemon.pokemonTester.service.adminPokemon.AdminPokemonService;
import com.pokemon.pokemonTester.util.Constant;

import java.util.HashMap;

@RestController
@RequestMapping(ApiPath.ADMIN_PATH_POKEMON)
public class AdminPokemonController {

    private AdminPokemonService adminPokemonService;

    public AdminPokemonController() {
        this.adminPokemonService = new AdminPokemonService();
    }

    @GetMapping(ApiPath.GET_POKEMON)
    public ResponseEntity<GeneralResponse> getPokemon(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer id) {
        GeneralResponse generalResponse = new GeneralResponse(HttpStatus.OK, new HashMap<>(), "", "");
        try {
            if (name != null || id != null) {
                PokemonDetailModel detailPokemon = adminPokemonService.getPokemon(name, id);
                generalResponse.setResponse(detailPokemon);
            } else {
                generalResponse = ResponseUtils.ErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        Constant.PARAMS_WARNING_1 + "(name o el id)"
                );
            }
        } catch (HttpClientErrorException e) {
            generalResponse = ResponseUtils.ErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        } catch (Exception e) {
            generalResponse = ResponseUtils.ErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    Constant.RESPONSE_ERROR_1
            );
        }
        return ResponseEntity.ok(generalResponse);

    }

}
