package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;

    @Before
    public void setUp() {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        
        // These are necessary
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        IPokedex mockPokedex = Mockito.mock(IPokedex.class);

        // Define the behavior of pokemonTrainerFactory to return a PokemonTrainer with a mock Pokedex
        Mockito.when(pokemonTrainerFactory.createTrainer("lorem", Team.MYSTIC, pokedexFactory)) .thenReturn(new PokemonTrainer("lorem", Team.MYSTIC, mockPokedex));
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);

        // Vérifier si le trainer n'est pas NULL
        assertNotNull(trainer);

        assertEquals("Ash", trainer.getName());
        assertEquals(Team.MYSTIC, trainer.getTeam());
    }

    @Test
    public void testTrainerHasPokedex() {
        // Create a trainer using the factory
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);

        // Verify that the trainer's pokedex is not null
        assertNotNull(trainer.getPokedex());
    }

    // Test if PokemonTrainer creation does not throw exceptions
    @Test
    public void testCreateTrainerWithoutException() {
        try {
            // Try creating a PokemonTrainer
            PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);
        } catch (Exception e) {
            fail("Cet entraîneur est valide et ne devrait pas causer d'exceptions.");
        }
    }
}
