package Main.entity;

import java.util.Random;

import Main.graphics.Screen;
import Main.level.Level;

public class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	}
	
	public void render (Screen screen) {
	}
	
	public void remove() {
		//remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
}
