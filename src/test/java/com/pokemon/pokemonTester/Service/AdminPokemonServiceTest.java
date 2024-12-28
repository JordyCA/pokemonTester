package com.pokemon.pokemonTester.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

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
import org.springframework.web.client.RestTemplate;

import com.pokemon.pokemonTester.Model.ResponseAdminPokemonModel.PokemonDetailModel;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminPokemonServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private AdminPokemonService service;

	@DisplayName("Test testGetPokemonIsPikachu")
	@ParameterizedTest
	@MethodSource("dataGetPokemonSuccess")
	void testGetPokemonIsPikachu(String name, Integer id) {

		String jsonResonse = "{\"body\":{\"base_experience\":112,\"forms\":[{\"name\":\"pikachu\",\"url\":\"https://pokeapi.co/api/v2/pokemon-form/25/\"}],\"height\":4,\"id\":25,\"weight\":60}}";

		when(restTemplate.getForEntity(any(), any())).thenReturn(new ResponseEntity<>(jsonResonse, HttpStatus.OK));

		PokemonDetailModel pokemonDetail = service.getPokemon(name, id);

		assertTrue(pokemonDetail.getName().equals("pikachu"));
	}
	
	@Test
	@DisplayName("Test testGetPokemonHttpClientErrorException")
	void testGetPokemonHttpClientErrorException() {

		when(restTemplate.getForEntity(any(), any())).thenThrow(HttpClientErrorException.class);
		try {
			service.getPokemon("pikachu", null);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpClientErrorException.class, e);
		}
	}
	
	@Test
	@DisplayName("Test testGetPokemonException")
	void testGetPokemonException() {

		when(restTemplate.getForEntity(any(), any())).thenThrow(NullPointerException.class);
		try {
			service.getPokemon("pikachu", null);
		} catch (Exception e) {
			assertEquals(Exception.class, e);
		}
	}

	@DisplayName("Test testGetPokemonSuccess")
	private static Stream<Object[]> dataGetPokemonSuccess() {
		return Stream.of(new Object[] { null, 25 }, new Object[] { "pikachu", 0 }, new Object[] { "pikachu", null });
	}

	@ParameterizedTest
	@MethodSource("dataGetPokemonSuccess")
	void testGetPokemonSucces(String name, Integer id) {

		String jsonResonse = "{\"body\":{\"base_experience\":112,\"forms\":[{\"name\":\"pikachu\",\"url\":\"https://pokeapi.co/api/v2/pokemon-form/25/\"}],\"height\":4,\"id\":25,\"weight\":60}}";

		when(restTemplate.getForEntity(any(), any())).thenReturn(new ResponseEntity<>(jsonResonse, HttpStatus.OK));

		assertNotNull(service.getPokemon(name, id));
	}

}
