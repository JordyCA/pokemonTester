package com.pokemon.pokemonTester.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.awt.*;
import java.util.stream.Stream;

import com.pokemon.pokemonTester.dto.GeneralResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.pokemon.pokemonTester.dto.AdminPokemonResponseDto.PokemonDetailModel;
import com.pokemon.pokemonTester.service.adminPokemon.AdminPokemonService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminPokemonControllerTest {

	@Mock
	private AdminPokemonService adminPokemonService;

	@InjectMocks
	private AdminPokemonController controller;

	@DisplayName("Test testGetPokemonIsPikachu")
	@ParameterizedTest
	@MethodSource("dataGetPokemonSuccess")
	void testGetPokemonIsPikachu(String name, Integer id) {
		String jsonResponseService = "{\"id\":25,\"name\":\"pikachu\",\"baseexperience\":112,\"weight\":60,\"height\":4}";
		PokemonDetailModel responseService = new Gson().fromJson(jsonResponseService, PokemonDetailModel.class);
		when(adminPokemonService.getPokemon(any(), any())).thenReturn(responseService);

		ResponseEntity<GeneralResponse> response = controller.getPokemon(name, id);
		PokemonDetailModel responseBody = (PokemonDetailModel) response.getBody().getResponse();
//		PokemonDetailModel reponseTem = new Gson().fromJson(response.getBody(), PokemonDetailModel.class);
		assertTrue(responseBody.getName().equals("pikachu"));
	}

	@Test
	@DisplayName("Test testGetPokemonException")
	void testGetPokemonException() {

		when(adminPokemonService.getPokemon(any(String.class), any(Integer.class)))
				.thenThrow(NullPointerException.class);
		try {
			controller.getPokemon("pikachu", null);
		} catch (Exception e) {
			assertEquals(Exception.class, e);
		}
	}
	
	@Test
	@DisplayName("Test testGetPokemonHttpClientErrorException")
	void testGetPokemonHttpClientErrorException() {

		when(adminPokemonService.getPokemon(any(String.class), any(Integer.class)))
				.thenThrow(HttpClientErrorException.class);
		try {
			controller.getPokemon("pikachu", null);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpClientErrorException.class, e);
		}
	}

	@DisplayName("Test testGetPokemonSuccess")
	private static Stream<Object[]> dataGetPokemonSuccess() {
		return Stream.of(new Object[] { "", 25 }, new Object[] { null, 25 }, new Object[] { "pikachu", 0 },
				new Object[] { "pikachu", null });
	}

	@ParameterizedTest
	@MethodSource("dataGetPokemonSuccess")
	void testGetPokemonSuccess(String name, Integer id) {
		String jsonResponseService = "{\"id\":25,\"name\":\"pikachu\",\"baseexperience\":112,\"weight\":60,\"height\":4}";
		PokemonDetailModel responseService = new Gson().fromJson(jsonResponseService, PokemonDetailModel.class);
		when(adminPokemonService.getPokemon(any(), any())).thenReturn(responseService);

		ResponseEntity<GeneralResponse> response = controller.getPokemon(name, id);
		System.out.println(response.getBody().getCode());
//		PokemonDetailModel responseBody = (PokemonDetailModel) response.getBody().getResponse();
	    assertTrue(response.getBody().getCode().equals(String.valueOf(HttpStatus.OK.value())));
	}

}
