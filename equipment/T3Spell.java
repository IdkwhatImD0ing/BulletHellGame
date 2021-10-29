package equipment;

import java.awt.Toolkit;

import sprite.Player;

public class T3Spell extends Item {

	private int spellDamage;
	private int abilityCost;

	public T3Spell(Player player) {
		super(player, "src/Abilities/T3Spell.png", "ability", true);
		spellDamage = 140;
		abilityCost = 100;
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/LightBlueBall.png");
	}

	public int getDamage() {
		return spellDamage;
	}

	public int getAbilityCost() {
		return abilityCost;
	}

	public String getInfo() {
		return "Cost: " + abilityCost + " \nDamage: " + spellDamage;
	}

}
