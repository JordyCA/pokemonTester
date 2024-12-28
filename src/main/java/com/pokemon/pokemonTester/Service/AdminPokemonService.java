package com.pokemon.pokemonTester.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.pokemon.pokemonTester.Model.ResponseAdminPokemonModel.PokemonDetailModel;
import com.pokemon.pokemonTester.Model.ResponseAdminPokemonModel.ResponsePokemonDetailModel;
import com.pokemon.pokemonTester.Utils.Constant;

@Service
public class AdminPokemonService {

	public PokemonDetailModel getPokemon(String name, Integer id) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = name != null ? Constant.URL_POKEMON_CENTRAL + name.replace("\"", "")
					: id != null ? Constant.URL_POKEMON_CENTRAL + id : "";
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			ResponsePokemonDetailModel pokemonDetail = new Gson().fromJson(response.getBody().replace("_", ""),
					ResponsePokemonDetailModel.class);
			return orderData(pokemonDetail);
		} catch (HttpClientErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new NullPointerException(e.getMessage());
		}
	}

	private PokemonDetailModel orderData(ResponsePokemonDetailModel responseData) {
		PokemonDetailModel orderData = new PokemonDetailModel();
		orderData.setId(responseData.getId());
		orderData.setBaseexperience(responseData.getBaseexperience());
		orderData.setName(responseData.getForms().get(0).getName());
		orderData.setHeight(responseData.getHeight());
		orderData.setWeight(responseData.getWeight());
		return orderData;
	}
}
