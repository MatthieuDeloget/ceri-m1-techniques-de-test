package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;
    private PokemonMetadata metadata;

    @Before
    public void setUp() {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        
        // Metadata du pokémon
        metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        
        // Mock pour création de pokémon
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 613, 64, 4000, 4, 56, 126, 126, 90);
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon);
    }

    // Test if PokemonFactory creates a Pokemon with correct attributes
    @Test
    public void testCreatePokemon() {
        // Create a Pokemon using the factory
        Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        
        // Verify that the Pokemon is not null
        assertNotNull(pokemon);

        // Check if the attributes of the created Pokemon are correct
        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(56, pokemon.getIv(), 0.01);  // Allow small delta for double comparison
    }

    // Test if the metadata for the Pokemon is correct
    @Test
    public void testPokemonMetadata() {
        Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        // Check if the metadata is correct
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
    }

    // Test if the Pokemon creation doesn't throw any exception
    @Test
    public void testCreatePokemonWithoutException() {
        try {
            // Try creating a Pokemon
            Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        } catch (Exception e) {
            fail("La création de ce pokémon ne devrait pas causer d'erreurs.");
        }
    }
}
