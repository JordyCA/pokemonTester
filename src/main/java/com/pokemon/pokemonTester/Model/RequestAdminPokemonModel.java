package com.pokemon.pokemonTester.Model;

import lombok.Getter;
import lombok.Setter;


public class RequestAdminPokemonModel {
	
	@Getter
	@Setter
	public static class ParamsGetPokemon {
		private String nombre;
		private Integer id;
	}

}
