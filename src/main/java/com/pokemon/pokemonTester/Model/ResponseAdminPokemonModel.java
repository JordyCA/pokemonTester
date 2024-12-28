package com.pokemon.pokemonTester.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAdminPokemonModel {

	@Getter
	@Setter
	public static class ResponseGetPokemon {
		private String message;
	}

	@Getter
	@Setter
	public static class ResponsePokemonDetailModel {
		private Integer id;
		private List<ResponsePokemonFormsModel> forms;	
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
