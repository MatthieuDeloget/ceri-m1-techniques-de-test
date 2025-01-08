package fr.univavignon.pokedex.api;

import java.util.Random;

public class PokemonFactory implements IPokemonFactory {

    private PokemonMetadataProvider pokemonMetadataProvider;

    public PokemonFactory(PokemonMetadataProvider pokemonMetadataProvider) {
        this.pokemonMetadataProvider = pokemonMetadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(index);

            Random randomGenerator = new Random();
            int attackIV = randomGenerator.nextInt(16);
            int defenseIV = randomGenerator.nextInt(16);
            int staminaIV = randomGenerator.nextInt(16);
            double iv = (attackIV + defenseIV + staminaIV) * 45 / 100;

            return new Pokemon(
                index, 
                metadata.getName(), 
                metadata.getAttack() + attackIV, 
                metadata.getDefense() + defenseIV,
                metadata.getStamina() + staminaIV, 
                cp, 
                hp, 
                dust, 
                candy, 
                iv);
        } catch (PokedexException e) {
            throw new ArrayIndexOutOfBoundsException(
                "Pokemon not found with index " + String.valueOf(index));
        }
    }
}
