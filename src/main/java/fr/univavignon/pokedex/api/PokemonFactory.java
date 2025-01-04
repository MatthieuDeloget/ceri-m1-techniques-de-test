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
            int attack_iv = randomGenerator.nextInt(16);
            int defense_iv = randomGenerator.nextInt(16);
            int stamina_iv = randomGenerator.nextInt(16);
            double iv = (attack_iv + defense_iv + stamina_iv)*45/100;

            return new Pokemon(
                index, 
                metadata.getName(), 
                metadata.getAttack() + attack_iv, 
                metadata.getDefense() + defense_iv,
                metadata.getStamina() + stamina_iv, 
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
