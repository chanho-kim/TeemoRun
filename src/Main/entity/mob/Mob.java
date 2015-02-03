package Main.entity.mob;

import Main.entity.Entity;
import Main.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move() {}
	
	public void update() {}
	
	private boolean collision() {
		return false;
	}
}
