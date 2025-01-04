package fr.univavignon.pokedex.api;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {    

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, new PokemonFactory(metadataProvider));
        return new PokemonTrainer(name, team, pokedex);
    }   
}
