package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {
	
	private IPokedex pokedex;
	
	private IPokemonMetadataProvider metadataProvider;
	private IPokemonFactory pokemonFactory;
	
	PokemonMetadata bulbizarreMetadata;
	PokemonMetadata aqualiMetadata;
	Pokemon bulbizarre;
	Pokemon aquali;
	
	List<Pokemon> pokemonList;
	
	@Before
	public void setUp() {
		// The Mock(tm)'s mocked friends
		metadataProvider = mock(IPokemonMetadataProvider.class);
		pokemonFactory = mock(IPokemonFactory.class);
		// The Mock(tm)
		pokedex = mock(IPokedex.class);
		
		// Pokemon examples
		bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
		bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
		
		aqualiMetadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);
		aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
		
		// Mocked behavior
		when(pokedex.size()).thenReturn(2);
		try {
			when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);
			when(metadataProvider.getPokemonMetadata(133)).thenReturn(aqualiMetadata);
		} catch (PokedexException e) {}
		when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);
		
		// Add pokemons to the list
		pokemonList = new ArrayList<>();
		pokemonList.add(bulbizarre);
		pokemonList.add(aquali);
		when(pokedex.getPokemons()).thenReturn(pokemonList);
	}
	
	@Test
	public void testSize() {
		assertEquals(2, pokedex.size());
	}

	@Test
	public void testAddPokemon() {
		assertEquals(0, pokedex.addPokemon(bulbizarre));
		assertEquals(133, pokedex.addPokemon(aquali));
	}
	
	@Test
	public void testGetPokemon(int id) throws PokedexException {
		PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
		assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
	}
	
	@Test(expected = PokedexException.class)
	public void testGetPokemonInvalidIndex(int id) throws PokedexException {
		when(pokedex.getPokemon(-702)).thenThrow(new PokedexException("Invalid index"));
		pokedex.getPokemon(-702);
	}
	
	@Test
	public void testGetPokemons() {
		assertEquals(pokemonList, pokedex.getPokemons());
	}
	
	
	
}
