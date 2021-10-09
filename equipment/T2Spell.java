package equipment;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

public class T2Spell extends Item{

	private int spellDamage;
	private int abilityCost;
	
	public T2Spell(Player player) {
		super(player, "src/Abilities/T2Spell.png", "ability", true);
		spellDamage = 100;
		abilityCost = 60;
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/BlueBall.png");
	}
	

	public int getDamage() {
		return spellDamage;
	}
	
	public int getAbilityCost() {
		return abilityCost;
	}

	
	public String getInfo() {
		return "Cost: " + abilityCost 
				+ " \nDamage: " + spellDamage;
	}
}
