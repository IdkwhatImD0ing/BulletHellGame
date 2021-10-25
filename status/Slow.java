package status;

import sprite.Player;

public class Slow extends Status{

	private int decreasedSpeed;
	public Slow(double duration, Player player) {
		super(duration, player);
	}
	
	public void applyStatus(Player player) {
		decreasedSpeed = player.getSpeed()/2;
		player.increaseSpd(-1 * decreasedSpeed);
	}
	
	public void removeStatus(Player player) {
		decreasedSpeed = player.getSpeed()/2;
		player.increaseSpd(decreasedSpeed);
	}

}
