package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        
        // Nous avons besoin de ces classes pour faire fonctionner PokedexFactory
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        
        IPokedex pokedex = Mockito.mock(IPokedex.class);
        Mockito.when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    // Un nouveau Pokédex doit être
    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        
        assertNotNull(pokedex);
    }

    // Un nouveau Pokédex doit être vide
    @Test
    public void testCreatedPokedexIsEmpty() {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        
        // Mock fonction size()
        Mockito.when(pokedex.size()).thenReturn(0);
        
        assertEquals(0, pokedex.size());
    }

    // On doit pouvoir ajouter un pokémon
    @Test
    public void testAddPokemonToPokedex() throws PokedexException {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

		Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        
        int index = pokedex.addPokemon(bulbizarre);
        assertEquals(0, index);
    }
}
