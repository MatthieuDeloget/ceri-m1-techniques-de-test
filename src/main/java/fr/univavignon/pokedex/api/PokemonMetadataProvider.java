package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides metadata for Pokémon species by reading from a CSV file.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    // Internal map to store Pokémon metadata
    private final Map<Integer, PokemonMetadata> metadataMap;

    public PokemonMetadataProvider() {
        metadataMap = new HashMap<>();
        try {
            initializeMetadata();
        } catch (IOException e) {
            System.err.println("Error reading metadata from CSV: " + e.getMessage());
        }
    }

    /**
     * Reads metadata from a CSV file and populates the metadata map.
     * @param csvFile Path to the CSV file containing the Pokémon metadata.
     * @throws IOException if there is an error reading the file.
     */
    private void initializeMetadata() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("../../../../../../../pokemon.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length != 5)
                    continue;

                int index = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim(); // Already a String
                int attack = Integer.parseInt(fields[2].trim());
                int defense = Integer.parseInt(fields[3].trim());
                int stamina = Integer.parseInt(fields[5].trim());

                metadataMap.put(index, new PokemonMetadata(index, name, attack, defense, stamina));
            }
        }
    }

    /**
     * Fetches the metadata for a given Pokémon index.
     * @param index Pokémon index
     * @return Metadata of the Pokémon
     * @throws PokedexException if the index is invalid
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (!metadataMap.containsKey(index)) {
            throw new PokedexException("Invalid Pokémon index: " + index);
        }
        return metadataMap.get(index);
    }
}
