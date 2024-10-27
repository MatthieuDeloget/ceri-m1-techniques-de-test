package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

	// Ce fichier a été écrit par ChatGPT, puis je l'ai révisé pour comprendre.
	
    private IPokemonMetadataProvider metadataProvider;

    @Before
    public void setUp() {
        // On utilise Mockito pour créer un mock de l'interface IPokemonMetadataProvider
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        
        // Définition des comportements du mock, exemple pour Bulbizarre (index 0)
        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        try {
            when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);
        } catch (PokedexException e) {
        	System.out.println("IPokemonMetadataProviderTest.java , line 24, PokedexException");
        }
    }
 
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // On récupère les métadonnées pour le Pokémon d'index 0
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);

        // On vérifie que les métadonnées retournées correspondent à ce que nous attendons
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        // Teste si une exception est levée pour un index invalide
        when(metadataProvider.getPokemonMetadata(-701)).thenThrow(new PokedexException("Invalid index"));
        metadataProvider.getPokemonMetadata(-701);
    }
}

