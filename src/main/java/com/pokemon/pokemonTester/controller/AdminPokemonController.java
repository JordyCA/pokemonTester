package com.pokemon.pokemonTester.controller;

import com.pokemon.pokemonTester.common.ApiPath;
import com.pokemon.pokemonTester.dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.pokemon.pokemonTester.dto.ErrorResponse;
import com.pokemon.pokemonTester.dto.AdminPokemonResponseDto.PokemonDetailModel;
import com.pokemon.pokemonTester.service.adminPokemon.AdminPokemonService;
import com.pokemon.pokemonTester.util.Constant;

@RestController
@RequestMapping(ApiPath.ADMIN_PATH_POKEMON)
public class AdminPokemonController {
	
	private AdminPokemonService adminPokemonService;
	
	public AdminPokemonController() {
		this.adminPokemonService = new AdminPokemonService();
	}
	
	@GetMapping(ApiPath.GET_POKEMON)
	public ResponseEntity<GeneralResponse> getPokemon(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) Integer id) { 
		try {
			if (name != null || id != null) {
				PokemonDetailModel detailPokemon = adminPokemonService.getPokemon(name, id);
				return ResponseEntity.ok(new GeneralResponse(HttpStatus.OK, detailPokemon, "", ""));
			} else {
				ErrorResponse responseError = new ErrorResponse();
				responseError.setCodigo(404);
				responseError.setMessage(Constant.PARAMS_WARNING_1 + "(name o el id)");
				return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST, responseError, "", ""));
			}
		}
		catch (HttpClientErrorException e) {
			ErrorResponse responseError = new ErrorResponse();
			responseError.setCodigo(400);
			responseError.setMessage(e.getMessage());
			return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST, responseError, "", ""));
		}
		catch (Exception e) {
			ErrorResponse responseError = new ErrorResponse();
			responseError.setCodigo(400);
			responseError.setMessage(Constant.RESPONSE_ERROR_1);
			return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST, responseError, "", ""));
		}
		
	}

}
