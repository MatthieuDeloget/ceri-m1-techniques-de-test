package fr.univavignon.pokedex.api;

/**
 * Pokemon metadata POJO.
 * 
 * @author fv
 */
public class PokemonMetadata {

	/** Pokemon index. **/
	private final int index;

	/** Pokemon name. **/
	private final String name;

	/** Pokemon attack level. **/
	private final int attack;

	/** Pokemon defense level. **/
	private final int defense;

	/** Pokemon stamina level. **/
	private final int stamina;

	/**
	 * Default constructor.
	 * 
	 * @param index Pokemon index.
	 * @param name Pokemon name.
	 * @param attack Attack level.
	 * @param defense Defense level.
	 * @param stamina Stamina level.
	 */
	public PokemonMetadata(
		final int index, final String name, final int attack, 
		final int defense, final int stamina) {
		this.index = index;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.stamina = stamina;
	}
	
	/** Index getter. 
	 * @return ID of Pokemon.
	 * **/
	public int getIndex() {
		return index;
	}
	
	/** Name getter.
	 * @return The species' name of the Pokemon.
	 * **/
	public String getName() {
		return name;
	}

	/** Attack level getter. 
	 * @return The attack stat of the Pokemon.
	 * **/
	public int getAttack() {
		return attack;
	}

	/** Defense level getter. 
	 * @return The defense stat of the Pokemon.
	 * **/
	public int getDefense() {
		return defense;
	}

	/** Stamina level getter. 
	 * @return The stamina of the Pokemon.
	 * **/
	public int getStamina() {
		return stamina;
	}

}
