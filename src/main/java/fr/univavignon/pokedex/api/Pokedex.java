package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Pokedex implements IPokedex {

    private List<Pokemon> knownPokemons;
    private IPokemonFactory pokemonFactory;
    private IPokemonMetadataProvider metadataProvider;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.knownPokemons = new ArrayList<Pokemon>();
        this.pokemonFactory = pokemonFactory;
        this.metadataProvider = metadataProvider;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return metadataProvider.getPokemonMetadata(index);
    }

    @Override
    public int size() {
        return this.knownPokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        knownPokemons.add(pokemon);
        return this.size();
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        Pokemon p = knownPokemons.get(id);
        if (p == null) {
            throw new PokedexException("Pokemon not found in Pokedex");
        }
        return p;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.knownPokemons;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        this.knownPokemons.sort(order);
        return this.knownPokemons;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }
    
}
