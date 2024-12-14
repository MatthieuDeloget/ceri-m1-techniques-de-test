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

        Mockito.when(pokemonTrainerFactory.createTrainer("Sacha", Team.MYSTIC, pokedexFactory)) .thenReturn(new PokemonTrainer("Sacha", Team.MYSTIC, mockPokedex));
    }

    @Test
    public void createTrainerTest() {
        PokemonTrainer sacha = pokemonTrainerFactory.createTrainer("Sacha", Team.MYSTIC, pokedexFactory);

        assertNotNull(sacha);
        assertEquals("Sacha", sacha.getName());
        assertEquals(Team.MYSTIC, sacha.getTeam());
    }

    @Test
    public void trainerHasPokedexTest() {
        PokemonTrainer sacha = pokemonTrainerFactory.createTrainer("Sacha", Team.MYSTIC, pokedexFactory);

        // Verify that the trainer's pokedex is not null
        assertNotNull(sacha.getPokedex());
    }

    // Test if PokemonTrainer creation does not throw exceptions
    @Test
    public void testCreateTrainerWithoutException() {
        try {
            PokemonTrainer sacha = pokemonTrainerFactory.createTrainer("Sacha", Team.MYSTIC, pokedexFactory);
        } catch (Exception e) {
            fail("Cet entraîneur est valide et ne devrait pas causer d'exceptions.");
        }
    }
}
