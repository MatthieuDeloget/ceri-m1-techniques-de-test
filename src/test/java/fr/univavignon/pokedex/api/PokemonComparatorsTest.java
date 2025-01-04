package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonComparatorsTest {

    Pokemon bulbizarre;
    Pokemon aquali;

    @Before
    public void setup() {
		bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
		aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);


        // There isn't much mock to do here...
        // But code is code, and code is tested


    }

    @Test
    public void testCompareNames() {
        assertEquals(bulbizarre.getName().compareTo(aquali.getName()) ,PokemonComparators.NAME.compare(bulbizarre, aquali));
    }

    @Test
    public void testCompareIndexs() {
        assertEquals(Integer.compare(bulbizarre.getIndex(), aquali.getIndex()) ,PokemonComparators.INDEX.compare(bulbizarre, aquali));
    }

    @Test
    public void testCompareCP() {
        assertEquals(Integer.compare(bulbizarre.getCp(), aquali.getCp()) ,PokemonComparators.CP.compare(bulbizarre, aquali));
    }
}


