package equipment;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

public class T1Spell extends Item {

	private int spellDamage;
	private int abilityCost;

	public T1Spell(Player player) {
		super(player, "src/Abilities/T1Spell.png", "ability", true);
		spellDamage = 60;
		abilityCost = 60;
		equipItem();
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/WhiteBall.png");

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
